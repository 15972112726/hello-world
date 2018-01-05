package com.zz.w3gparser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by dell on 2018/1/5.
 */
public class Test3 {
    public static void main(String[] args) throws DataFormatException, IOException, W3GException {

    Replay replay = new Replay(new File("D:/games/Warcraft3/Replay/11Game/_规范恶搞_ID101_17-11-11-14-20.w3g"));
    Header header = replay.getHeader();
        System.out.println("版本：1." + header.getVersionNumber() + "." + header.getBuildNumber());
    long duration = header.getDuration();
        System.out.println("时长：" + convertMillisecondToString(duration));

    UncompressedData uncompressedData = replay.getUncompressedData();
        System.out.println("游戏名称：" + uncompressedData.getGameName());
        System.out.println("游戏创建者：" + uncompressedData.getCreaterName());
        System.out.println("游戏地图：" + uncompressedData.getMap());

    List<Player> list = uncompressedData.getPlayerList();
        for(Player player : list) {
        System.out.println("---玩家" + player.getPlayerId() + "---");
        System.out.println("玩家名称：" + player.getPlayerName());
        if(player.isHost()) {
            System.out.println("是否主机：主机");
        } else {
            System.out.println("是否主机：否");
        }
        System.out.println("游戏时间：" + convertMillisecondToString(player.getPlayTime()));
//        System.out.println("操作次数：" + player.getAction());
//        System.out.println("APM：" + player.getAction() * 60000 / player.getPlayTime());
        if(player.getTeamNumber() != 12) {
            System.out.println("玩家队伍：" + (player.getTeamNumber() + 1));
            switch(player.getRace()) {
                case 0x01:
                case 0x41:
                    System.out.println("玩家种族：人族");
                    break;
                case 0x02:
                case 0x42:
                    System.out.println("玩家种族：兽族");
                    break;
                case 0x04:
                case 0x44:
                    System.out.println("玩家种族：暗夜精灵");
                    break;
                case 0x08:
                case 0x48:
                    System.out.println("玩家种族：不死族");
                    break;
                case 0x20:
                case 0x60:
                    System.out.println("玩家种族：随机");
                    break;
            }
            switch(player.getColor()) {
                case 0:
                    System.out.println("玩家颜色：红");
                    break;
                case 1:
                    System.out.println("玩家颜色：蓝");
                    break;
                case 2:
                    System.out.println("玩家颜色：青");
                    break;
                case 3:
                    System.out.println("玩家颜色：紫");
                    break;
                case 4:
                    System.out.println("玩家颜色：黄");
                    break;
                case 5:
                    System.out.println("玩家颜色：橘");
                    break;
                case 6:
                    System.out.println("玩家颜色：绿");
                    break;
                case 7:
                    System.out.println("玩家颜色：粉");
                    break;
                case 8:
                    System.out.println("玩家颜色：灰");
                    break;
                case 9:
                    System.out.println("玩家颜色：浅蓝");
                    break;
                case 10:
                    System.out.println("玩家颜色：深绿");
                    break;
                case 11:
                    System.out.println("玩家颜色：棕");
                    break;
            }
            System.out.println("障碍（血量）：" + player.getHandicap() + "%");
            if(player.isComputer()) {
                System.out.println("是否电脑玩家：电脑玩家");
                switch (player.getAiStrength())
                {
                    case 0:
                        System.out.println("电脑难度：简单的");
                        break;
                    case 1:
                        System.out.println("电脑难度：中等难度的");
                        break;
                    case 2:
                        System.out.println("电脑难度：令人发狂的");
                        break;
                }
            } else {
                System.out.println("是否电脑玩家：否");
            }
        } else {
            System.out.println("玩家队伍：裁判或观看者");
        }

    }

    List<ChatMessage> chatList = uncompressedData.getReplayData().getChatList();
        for(ChatMessage chatMessage : chatList) {
        String chatString = "[" + convertMillisecondToString(chatMessage.getTime()) + "]";
        chatString += chatMessage.getFrom().getPlayerName() + " 对 ";
        switch ((int)chatMessage.getMode()) {
            case 0:
                chatString += "所有人";
                break;
            case 1:
                chatString += "队伍";
                break;
            case 2:
                chatString += "裁判或观看者";
                break;
            default:
                chatString += chatMessage.getTo().getPlayerName();
        }
        chatString += " 说：" + chatMessage.getMessage();
        System.out.println(chatString);
    }
}

    private static String convertMillisecondToString(long millisecond) {
        long second = (millisecond / 1000) % 60;
        long minite = (millisecond / 1000) / 60;
        if (second < 10) {
            return minite + ":0" + second;
        } else {
            return minite + ":" + second;
        }
    }
}
