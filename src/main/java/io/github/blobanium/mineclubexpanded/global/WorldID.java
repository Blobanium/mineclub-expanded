package io.github.blobanium.mineclubexpanded.global;

import io.github.blobanium.mineclubexpanded.games.tabletop.RichPresenceTabletopChatListener;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;

public class WorldID {
    public static int worldID;
    public static String tabletopusername;


    public static void updateWorldID(){
        worldID = newWorldID();
    }

    public static void sendPresence(){
        String state = generateStateString();
        if(!state.equals("")){
            DiscordRP.updateStatus(state, generateDetailsString());
        }
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
    public static final int INFECTED = 303;

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
            case "gamemap_admin_event_infected" -> INFECTED;

            default -> getStartsWithID(WorldListener.worldName);
        };
    }

    private static int getStartsWithID(String id){
        //Housing
        if(id.startsWith("housing"))        {return HOUSING;}

        //Tabletop Games
        else if(id.startsWith("connect_four")){return CONNECT_4;}
        else if(id.startsWith("match_five"))  {return MATCH_5;}
        else if(id.startsWith("lucky_shot"))  {return LUCKY_SHOT;}
        else if(id.startsWith("tic_tac_toe")) {return TIC_TAC_TOE;}
        else if(id.startsWith("sumo"))        {return SUMO;}
        else if(id.startsWith("tag"))         {return TAG;}
        else if(id.startsWith("snowball"))    {return SNOWBALL_FIGHT;}
        else if(id.startsWith("shoot"))       {return SHOOT_THE_SHEEP;}

        //Return Unknown if a world is not on this list.
        else {return UNKNOWN;}
    }

    public static String getName(){
        return switch (worldID){
            case UNKNOWN -> "Unknown";
            case LOBBY -> "Lobby";
            case HOUSING -> "Housing";
            case BATTLE_DOME -> "Battle Dome";
            case SLIME_WALLS -> "Slime Walls";
            case LASER_TAG -> "Laser Tag";
            case DODGE_BALL -> "Dodge Ball";
            case CONNECT_4 -> "Connect 4";
            case MATCH_5 -> "Match 5";
            case LUCKY_SHOT -> "Lucky Shot";
            case TIC_TAC_TOE -> "Tic Tac Toe";
            case SUMO -> "Sumo";
            case TAG -> "Tag";
            case SNOWBALL_FIGHT -> "Snowball Fight";
            case SHOOT_THE_SHEEP -> "Shoot The Sheep";
            case TNT_RUN -> "TNT Run";
            case SPLEEF -> "Spleef";
            case BRAWL -> "Brawl";
            case INFECTED -> "Infected";
            default -> throw new IllegalStateException("Unexpected value: " + worldID);
        };
    }

    public static String getWorldCatagory(){
        return switch (worldID){
            case LOBBY -> "Lobby";
            case HOUSING -> "Housing";
            case BATTLE_DOME, SLIME_WALLS, LASER_TAG, DODGE_BALL -> "Main";
            case CONNECT_4, MATCH_5, LUCKY_SHOT, TIC_TAC_TOE, SUMO, TAG, SNOWBALL_FIGHT, SHOOT_THE_SHEEP -> "Tabletop";
            case TNT_RUN, SPLEEF, BRAWL, INFECTED -> "Admin Event";
            default -> throw new IllegalStateException("Unexpected value: " + worldID);
        };
    }

    public static String generateStateString() {
        String category = getWorldCatagory();
        return switch (category) {
            case "Lobby" -> "Currently in the lobby";
            case "Main" -> "Currently Playing " + getName();
            case "Tabletop" -> "Tabletop: " + getName();
            case "Admin Event" -> "Admin Event: " + getName();
            default -> "";
        };
    }

    public static String generateDetailsString() {
        return switch (worldID) {
            case CONNECT_4, MATCH_5, LUCKY_SHOT, TIC_TAC_TOE, SUMO, TAG, SNOWBALL_FIGHT, SHOOT_THE_SHEEP -> "Playing with " + RichPresenceTabletopChatListener.matchedUsername;
            default -> DiscordRP.defaultDetails();
        };
    }
}
