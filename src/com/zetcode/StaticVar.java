package com.zetcode;

public class StaticVar {
    public static int Xoffset = 0;//거리
    public static int Yoffset = 0;//거리
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
            +"#   #   # \n"
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
