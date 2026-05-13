package Service;

import Service.Util.Universal.InitHandler;

public class InitProject {

    /**
     * 新建一个项目用init
     * 后续新增ao与ro用add
     *
     */
    public static void main(String[] args) {
        // 添加Ao，Ro
        InitHandler.init("");
        // 批量添加单接口测试用例
       // InitHandler.addBatchCase();
    }


}
