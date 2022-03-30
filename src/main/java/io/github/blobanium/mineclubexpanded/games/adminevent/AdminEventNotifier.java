package io.github.blobanium.mineclubexpanded.games.adminevent;

public class AdminEventNotifier {
    public static boolean adminEventPending = false;

    public static void checkEvent(){
        AdminEventDecoder.decodeBossBar();
        if(AdminEventDecoder.adminEvent != null && !adminEventPending){
            System.out.println("triggered");
            adminEventPending = true;
        } else if (AdminEventDecoder.adminEvent == null && adminEventPending){
            adminEventPending = false;
        }
    }
}
