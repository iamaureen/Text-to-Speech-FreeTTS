/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import javax.sound.sampled.AudioFileFormat;

/**
 *
 * @author Ishrat Ahmed
 * @reference http://stackoverflow.com/questions/4027853/how-can-i-store-output-voice-to-an-audio-file-in-freetts
 */
public class TextToAudioFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        FreeTTS freetts;
        AudioPlayer audioPlayer = null;
        String voiceName = "kevin16";

        System.out.println();
        System.out.println("Using voice: " + voiceName);

        /* The VoiceManager manages all the voices for FreeTTS.
         */
        VoiceManager voiceManager = VoiceManager.getInstance();
        com.sun.speech.freetts.Voice helloVoice = voiceManager.getVoice(voiceName);

        if (helloVoice == null) {
            System.err.println(
                    "Cannot find a voice named "
                    + voiceName + ".  Please specify a different voice.");
            System.exit(1);
        }

        /* Allocates the resources for the voice.
         */
        helloVoice.allocate();

        /* Synthesize speech.
         */
        //create a audioplayer to dump the output file
        audioPlayer = new SingleFileAudioPlayer("E://output", AudioFileFormat.Type.WAVE);
        //attach the audioplayer 
        helloVoice.setAudioPlayer(audioPlayer);

        helloVoice.speak("Thank you for giving me a voice. "
                + "I'm so glad to say hello to this world.");

        /* Clean up and leave.
         */
        helloVoice.deallocate();
        //don't forget to close the audioplayer otherwise file will not be saved
        audioPlayer.close();
        System.exit(0);
    }
    
}
