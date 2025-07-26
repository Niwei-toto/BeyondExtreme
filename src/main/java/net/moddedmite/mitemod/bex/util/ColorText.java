package net.moddedmite.mitemod.bex.util;

import net.minecraft.EnumChatFormatting;
import net.minecraft.Minecraft;

public class ColorText {
    private static final EnumChatFormatting[] fabulousness = new EnumChatFormatting[] {EnumChatFormatting.RED, EnumChatFormatting.YELLOW,
            EnumChatFormatting.GREEN, EnumChatFormatting.AQUA, EnumChatFormatting.BLUE, EnumChatFormatting.LIGHT_PURPLE};

    public static String makeFabulous(String input) {
        return Formatting(input, fabulousness, 1, 1);
    }

    private static final EnumChatFormatting[] sanic = new EnumChatFormatting[] {EnumChatFormatting.BLUE, EnumChatFormatting.BLUE, EnumChatFormatting.BLUE,
            EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE,
            EnumChatFormatting.WHITE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.RED, EnumChatFormatting.WHITE, EnumChatFormatting.GRAY,
            EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY,
            EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY,
            EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY};

    public static String makeSANIC(String input) {
        return Formatting(input, sanic, 1, 1);
    }

    private static final EnumChatFormatting[] colorful = new EnumChatFormatting[] {EnumChatFormatting.BLUE, EnumChatFormatting.BLUE, EnumChatFormatting.BLUE,
            EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE,
            EnumChatFormatting.WHITE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.RED, EnumChatFormatting.WHITE, EnumChatFormatting.GRAY,
            EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY,
            EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY,
            EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY};

    public static String makeColor(String input) {
        return Formatting(input, colorful, 1, 1);
    }

    private static final EnumChatFormatting[] colors = new EnumChatFormatting[] {EnumChatFormatting.RED, EnumChatFormatting.DARK_RED,
            EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.DARK_GREEN, EnumChatFormatting.AQUA,
            EnumChatFormatting.DARK_AQUA, EnumChatFormatting.BLUE, EnumChatFormatting.LIGHT_PURPLE,
            EnumChatFormatting.DARK_PURPLE, EnumChatFormatting.BROWN,
            EnumChatFormatting.WHITE, EnumChatFormatting.LIGHT_GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.DARK_GRAY,EnumChatFormatting.BLACK,};

    public static String makeColors(String input) {
        return Formatting(input, colors, 1, 1);
    }

    public static String Formatting(String input, EnumChatFormatting[] colours, double delay, int posstep) {
        StringBuilder sb = new StringBuilder(input.length()*3);

        if (delay <= 0) {
            delay = 0.001;
        }

        Minecraft client = Minecraft.getMinecraft();
        long gameTime = 0;
        if (client.thePlayer.worldObj != null) {
            gameTime = client.thePlayer.worldObj.total_time;
        }

        int offset = (int) Math.floor(gameTime / delay) % colours.length;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            int col = ((i * posstep) + colours.length - offset) % colours.length;

            sb.append(colours[col].toString());
            sb.append(c);
        }

        return sb.toString();
    }
}