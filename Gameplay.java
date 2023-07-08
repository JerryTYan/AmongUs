public class Gameplay {
    public static void main(String[] args) {
        // Create BlueAstronauts
        BlueAstronaut alice = new BlueAstronaut("Alice", 15, 5, 25);
        BlueAstronaut john = new BlueAstronaut("John", 35, 4, 20);
        BlueAstronaut sam = new BlueAstronaut("Sam", 50, 2, 5);
        BlueAstronaut emma = new BlueAstronaut("Emma", 10, 1, 0);

        // Create RedAstronauts
        RedAstronaut lucas = new RedAstronaut("Lucas", 25, "novice");
        RedAstronaut stranger = new RedAstronaut("Stranger", 100, "expert");

        // RedAstronauts sabotaging BlueAstronauts
        lucas.sabotage(alice);
        System.out.println(alice);
        stranger.sabotage(john);
        System.out.println(john);

        // RedAstronauts attempting to freeze each other
        lucas.freeze(stranger);
        System.out.println(stranger);

        // Attempting to freeze a sus-level > 15 BlueAstronaut
        stranger.freeze(sam);
        System.out.println(sam);

        // BlueAstronauts calling meetings
        sam.emergencyMeeting();
        alice.emergencyMeeting();

        // Attempting to sabotage BlueAstronauts
        lucas.sabotage(alice);
        System.out.println(alice);

        // BlueAstronauts completing tasks
        john.completeTask();
        System.out.println(john);
        john.completeTask();
        System.out.println(john);

        // Attempting to freeze BlueAstronaut with sus-level < 15
        stranger.freeze(emma);
        System.out.println(emma);

        // Sabotaging and attempting to freeze a BlueAstronaut multiple times
        for (int i = 0; i < 5; i++) {
            lucas.sabotage(john);
            System.out.println(john);
        }
        stranger.freeze(john);
        System.out.println(john);

        // Sabotage and freeze remaining BlueAstronauts for Impostors to win
        lucas.sabotage(alice);
        stranger.freeze(alice);
        System.out.println(alice);
        lucas.sabotage(sam);
        stranger.freeze(sam);
        System.out.println(sam);

        // Only one BlueAstronaut remaining - Impostors win!
    }
}
