/**
 * Created by 198495 on 9/15/2017.
 */
public class GuitarHero {
    public static void main(String[] args) {
        GuitarString[] keyboard = new GuitarString[37];

        for(int i = 0; i < keyboard.length; i++) {
            keyboard[i] = new GuitarString(440*Math.pow(1.05956, (i-24)));
        }

        String keyNotes = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        char[] keyNotesChar = keyNotes.toCharArray();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for(int i = 0; i < keyNotesChar.length; i++) {
                    if (key == keyNotesChar[i]) {
                        keyboard[i].pluck();
                    }
                }
            }
            double sample = 0;
            for (int i = 0; i < keyboard.length; i++) {
                sample += keyboard[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < keyboard.length; i++) {
                keyboard[i].tic();
            }
        }
    }


}
