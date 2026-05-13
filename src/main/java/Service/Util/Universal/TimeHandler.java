package Service.Util.Universal;

public class TimeHandler {
    /**
     * 统计并打印一个无返回值方法的执行时间
     *
     * @param task     需要统计的方法（Runnable）
     * @param taskName 任务名称（用于打印）
     */
    public static void measure(Runnable task, String taskName) {
        long startTime = System.currentTimeMillis();
        task.run();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(taskName + " 执行完毕，执行时间: " + formatDuration(duration));
    }

    /**
     * 将毫秒数格式化为 时:分:秒.毫秒
     */
    public static String formatDuration(long durationMillis) {
        long hours = durationMillis / (1000 * 60 * 60);
        long minutes = (durationMillis / (1000 * 60)) % 60;
        long seconds = (durationMillis / 1000) % 60;
        long millis = durationMillis % 1000;
        return String.format("%02d:%02d:%02d.%03d (时:分:秒.毫秒)", hours, minutes, seconds, millis);
    }
} 