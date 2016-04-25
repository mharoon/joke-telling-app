package com.jokes;

import java.util.Random;

public class JokeBucket {

    private Random random = new Random();

    public String getJoke() {
        String joke;
        switch (random.nextInt(4)) {
            case 0:
                joke = "Q: What's the Object-Oriented way to become wealthy ? \n\n A: Inheritance";
                break;
            case 1:
                joke = "A guy is standing on the corner of the street smoking one cigarette after another. A lady walking by notices him and says\n" +
                        "\"Hey, don't you know that those things can kill you? I mean, didn't you see the giant warning on the box?!\"\n" +
                        "\"That's OK\" says the guy, puffing casually \"I'm a computer programmer\"\n" +
                        "\"So? What's that got to do with anything?\"\n" +
                        "\"We don't care about warnings. We only care about errors.\"";
                break;
            case 2:
                joke = "A programmer puts two glasses on his bedside table before going to sleep. A full one, in case he gets thirsty, and an empty one, in case he doesn't.";
                break;
            case 3:
                joke = "A son asked his father(a programmer) why the sun rises in the east, and sets in the west. His response?\n" +
                        "\n" +
                        "It works, don't touch!";
                break;
            default:
                joke = "Unix is user friendly. It's just very particular about who its friends are.";
                break;
        }
        return joke;
    }
}
