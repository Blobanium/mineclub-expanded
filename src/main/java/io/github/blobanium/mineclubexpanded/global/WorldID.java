package io.github.blobanium.mineclubexpanded.global;

public class WorldID {
    public static int worldID;


    public static void updateWorldID(){
        worldID = newWorldID();
    }

    //WorldIDs
    public static final int UNKNOWN = 0;
    public static final int LOBBY = 1;
    public static final int HOUSING = 2;

    public static final int BATTLE_DOME = 100;
    public static final int SLIME_WALLS = 101;
    public static final int LASER_TAG = 102;
    public static final int DODGE_BALL = 103;

    public static final int CONNECT_4 = 200;
    public static final int MATCH_5 = 201;
    public static final int LUCKY_SHOT = 202;
    public static final int TIC_TAC_TOE = 203;
    public static final int SUMO = 204;
    public static final int TAG = 205;
    public static final int SNOWBALL_FIGHT = 206;
    public static final int SHOOT_THE_SHEEP = 207;

    public static final int TNT_RUN = 300;
    public static final int SPLEEF = 301;
    public static final int BRAWL = 302;

    public static int newWorldID(){
        return switch (WorldListener.worldName){
            //Lobby, AFK Lounge, StaffHQ
            case "overworld" -> LOBBY;

            //Main Games
            case "gamemap_battle_dome" -> BATTLE_DOME;
            case "gamemap_slime_walls" -> SLIME_WALLS;
            case "gamemap_laser_tag" -> LASER_TAG;
            case "gamemap_dodge_ball" -> DODGE_BALL;

            //Admin Events
            case "gamemap_admin_event_tnt_run" -> TNT_RUN;
            case "gamemap_admin_event_spleef" -> SPLEEF;
            case "gamemap_admin_event_brawl" -> BRAWL;

            default -> getStartsWithID(WorldListener.worldName);
        };
    }

    private static int getStartsWithID(String id){
        //Housing
        if(id.startsWith("housing"))        {return HOUSING;}

        //Tabletop Games
        else if(id.startsWith("connect4"))  {return CONNECT_4;}
        else if(id.startsWith("match5"))    {return MATCH_5;}
        else if(id.startsWith("luckyshot")) {return LUCKY_SHOT;}
        else if(id.startsWith("ttt"))       {return TIC_TAC_TOE;}
        else if(id.startsWith("sumo"))      {return SUMO;}
        else if(id.startsWith("tag"))       {return TAG;}
        else if(id.startsWith("snowball"))  {return SNOWBALL_FIGHT;}
        else if(id.startsWith("shoot"))     {return SHOOT_THE_SHEEP;}

        //Return Unknown if a world is not on this list.
        else {return UNKNOWN;}
    }
}
