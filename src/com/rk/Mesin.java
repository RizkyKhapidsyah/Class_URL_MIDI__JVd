package com.rk;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Mesin extends JFrame {

    Sequence suara;
    Sequencer ambilSuara;

    public URL getURL(String namaFile) {
        URL url = null;
        try {
            url = this.getClass().getResource(namaFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return url;
    }

    public Mesin() {
        try {
            suara = MidiSystem.getSequence(getURL("rossa.mid"));
            ambilSuara = MidiSystem.getSequencer();
            ambilSuara.setSequence(suara);
            ambilSuara.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            ambilSuara.open();
            ambilSuara.start();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

        setTitle("Test URL MIDI");
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("Resolution : " + suara.getResolution(), 10, 50);
        g.drawString("Tick length : " + suara.getTickLength(), 10, 100);
        g.drawString("Tracks: " + suara.getTracks().length, 10, 150);
    }
}
