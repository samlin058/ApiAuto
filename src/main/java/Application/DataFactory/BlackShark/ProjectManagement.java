package Application.DataFactory.BlackShark;


import Data.Apis.BlackShark.AssetProjectController;
import Data.Apis.BlackShark.MyAssetProjectController;
import Data.Apis.BlackShark.PreAssetPkgController;
import Data.Apis.BlackShark.ProjectUserController;
import Data.Ro.BlackShark.*;
import Service.Enum.DatabaseEnum;
import Service.Util.Universal.JDBCHandler;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 项目管理
 */
public class ProjectManagement {
    public static void main(String[] args) {
        ProjectManagement projectManagement = new ProjectManagement();
        // projectManagement.createdProjectAndAddPkg("客群分案优化验证4",null);
        projectManagement.createdProjectAndAddPkg("20251225001-鸾刀测试",null);
    }

    static MyAssetProjectController myAssetProjectController = new MyAssetProjectController();
    PreAssetPkgController preAssetPkgController = new PreAssetPkgController();
    AssetProjectController assetProjectController = new AssetProjectController();
    ProjectUserController projectUserController = new ProjectUserController();

    /**
     * 项目管理——创建一个项目(默认鸾刀为项目BD)
     *
     * @param projectName 项目名
     * @param bdParam     bd参数
     */
    private static String createdProject(String projectName, MyAssetProjectControllerCreateRo.pm_MyAssetProjectControllerCreateRo bdParam) {
        MyAssetProjectControllerCreateRo myAssetProjectControllerCreateRo = new MyAssetProjectControllerCreateRo();
        if (StringHandler.isEmpty(bdParam)) {
            myAssetProjectControllerCreateRo.setProjectName(projectName);
            return myAssetProjectController.create(myAssetProjectControllerCreateRo);
        } else {
            myAssetProjectControllerCreateRo.setProjectName(projectName);
            myAssetProjectControllerCreateRo.setBdList(Arrays.asList(bdParam));
            return myAssetProjectController.create(myAssetProjectControllerCreateRo);
        }
    }

    /**
     * 项目管理——项目中添加一个资产包
     *
     * @param assetPkgName   资产包名
     * @param projectName    项目名
     */
    public void addAssetPackage(String assetPkgName, String projectName) {
        PreAssetPkgControllerAssetPkgBaseCreateRo preAssetPkgControllerAssetPkgBaseCreateRo = new PreAssetPkgControllerAssetPkgBaseCreateRo();
        String projectCode = searchJsonObjects(assetProjectController.list(), projectName);
        preAssetPkgControllerAssetPkgBaseCreateRo.setAssetName(assetPkgName);
        preAssetPkgControllerAssetPkgBaseCreateRo.setProjectCode(projectCode);
        preAssetPkgController.assetPkgBaseCreate(preAssetPkgControllerAssetPkgBaseCreateRo);
    }

    /**
     * 项目管理——给项目添加一个资产包（包名随机）
     *
     * @param projectName
     */
    public void addAssetPackage(String projectName) {
        PreAssetPkgControllerAssetPkgBaseCreateRo preAssetPkgControllerAssetPkgBaseCreateRo = new PreAssetPkgControllerAssetPkgBaseCreateRo();
        String projectCode = searchJsonObjects(assetProjectController.list(), projectName);
        preAssetPkgControllerAssetPkgBaseCreateRo.setProjectCode(projectCode);
        preAssetPkgController.assetPkgBaseCreate(preAssetPkgControllerAssetPkgBaseCreateRo);
    }

    /**
     * 项目管理——创建项目and生成一个测试包(默认bd为鸾刀)
     *
     * @param projectName 项目名
     */
    public void createdProjectAndAddPkg(String projectName, MyAssetProjectControllerCreateRo.pm_MyAssetProjectControllerCreateRo bdParam) {
        createdProject(projectName, bdParam);
        addAssetPackage(projectName);
        // 修改项目状态
        String sql = "UPDATE pre_asset_project SET process_state = \"EXECUTION\" WHERE project_name = ?;";
        JDBCHandler.AddDeleteAlter(DatabaseEnum.BlackShark, sql, projectName);
    }

    /**
     * 项目管理——给用户添加项目权限
     *
     * @param projectCode 项目code
     * @param userName    用户名
     */
    public void addProjectPermission(String projectCode, String userName) {
        String result = projectUserController.query(new ProjectUserControllerQueryRo(new ArrayList<>(Arrays.asList(projectCode))));
        Boolean flag1 = JsonHandler.existsInHttpResult(result, "projectUserList", "userName", userName);
        // 防止输入的是userCode
        Boolean flag2 = JsonHandler.existsInHttpResult(result, "projectUserList", "userCode", StringHandler.getPinyin(userName));
        if (flag1 || flag2) {
            System.out.println("[" + userName + "]已有该项目权限");
        } else {
            HashMap<Object, Object> user = new HashMap<>();
            user.put("userName", userName);
            user.put("userCode", StringHandler.getPinyin(userName));
            projectUserController.addUser(new ProjectUserControllerAddUserRo(new ArrayList<>(Arrays.asList(user)), new ArrayList<>(Arrays.asList(projectCode))));
        }
    }

    /**
     * 项目管理——删除用户的项目权限
     *
     * @param projectCode 项目code
     * @param userName    用户名
     */
    public  void removeProjectPermission(String projectCode, String userName) {
        String result = projectUserController.query(new ProjectUserControllerQueryRo(new ArrayList<>(Arrays.asList(projectCode))));
        Boolean flag1 = JsonHandler.existsInHttpResult(result, "projectUserList", "userName", userName);
        // 防止输入的是userCode
        Boolean flag2 = JsonHandler.existsInHttpResult(result, "projectUserList", "userCode", StringHandler.getPinyin(userName));
        if (flag1 || flag2) {
            HashMap<Object, Object> user = new HashMap<>();
            user.put("userName", userName);
            user.put("userCode", StringHandler.getPinyin(userName));
            projectUserController.removeUser(new ProjectUserControllerRemoveUserRo(new ArrayList<>(Arrays.asList(user)), new ArrayList<>(Arrays.asList(projectCode))));
        } else {
            System.out.println("[" + userName + "]没有该项目权限,无法移除该用户的权限");
        }
    }

    public  void addProjectPermissionByPkgNo(String assetPkgNo, String userName) {
        String sql = "select project_code projectCode from pre_asset_pkg_base where asset_pkg_no =? order by id desc;";
        String projectCode = JDBCHandler.queryFirstRowAsJson(DatabaseEnum.BlackShark, sql, assetPkgNo);
        addProjectPermission(projectCode, userName);
    }

    /**
     * 根据关键字，在json中获取对应对象的值（label,value）
     *
     * @param jsonString
     * @param keyword
     * @return
     */
    private static String searchJsonObjects(String jsonString, String keyword) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 解析 JSON 字符串
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode dataArray = rootNode.get("data");

            // 检查 data 字段是否存在并且是数组
            if (dataArray != null && dataArray.isArray()) {
                for (JsonNode item : dataArray) {
                    // 检查 label 和 value 是否包含关键字
                    String label = item.get("label").asText();
                    String value = item.get("value").asText();
                    if (label.equals(keyword)) {
                        return value;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}


