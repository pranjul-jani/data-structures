public interface PPG {
    public static int getGames() {
        return 5;
    }

    default int getGameCount() {
        return 0;
    }

}
