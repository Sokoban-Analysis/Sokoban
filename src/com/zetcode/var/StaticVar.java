package com.zetcode.var;

import com.zetcode.game.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class StaticVar {
    public static int Xoffset = 0;//거리
    public static int Yoffset = 0;//거리
    public static int[] score = new int[2];
    public static double[] time = new double[2];
    public static Player[] soko = new Player[2];
    public static ScheduledExecutorService scoreTimer;
    public static final int SPACE = 20;
    public static final int LEFT_COLLISION = 1;
    public static final int RIGHT_COLLISION = 2;
    public static final int TOP_COLLISION = 3;
    public static final int BOTTOM_COLLISION = 4;

    public static String level1
            ="####### \n" +
            "#@  # # \n" +
            "#   $ # \n" +
            "#   $ # \n" +
            "# ..  # \n" +
            "#  *  # \n" +
            "####### \n";

    public static String level2
            =" ####    \n" +
            "  #  ##### \n" +
            "### .#   # \n" +
            "#@  $    # \n" +
            "# ## ##### \n" +
            "#   $  #   \n" +
            "### .  #   \n" +
            "  #  ###   \n" +
            "  ####   \n";

    public static String level3
            ="######### \n"
            +"#       # \n"
            +"# # #$.## \n"
            +"#    $.#  \n"
            +"# # #$.#  \n"
            +"#       # \n"
            +"#####@  # \n"
            +"    ####  \n";

    public static String level4
            ="###############\n" +
            "# #   #   ## .#\n" +
            "#  # $    $  .#\n" +
            "#      #  #   #\n" +
            "#    $## ##   #\n" +
            "#$    #  .#  $#\n" +
            "#   ###  ##   #\n" +
            "#   ## $  #  ##\n" +
            "#...#     #  @#\n" +
            "###############\n";

    public static String level5
            = "    ######\n"
            + "    ##   #\n"
            + "    ##$  #\n"
            + "  ####  $##\n"
            + "  ##  $ $ #\n"
            + "#### # ## #   ######\n"
            + "##   # ## #####  ..#\n"
            + "## $  $          ..#\n"
            + "###### ### #@##  ..#\n"
            + "    ##     #########\n"
            + "    ########\n";

}
