import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;

public class Piano extends JFrame /*implements KeyListener, ActionListener*/ {
	
	JTextArea displayArea;
	static final String newline = System.getProperty("line.separator");
	private SourceDataLine line;
	private AudioInputStream a;
	private Clip[] myClips = new Clip[11];
	private Clip[] myClipsBlack = new Clip[7];
	private int clipCount = 0;
	private int clipCountBlack = 0;
	private boolean isShift = false;
	
	public Piano(String name) {
		super(name);
		
		createNote("a.wav");
		createNote("b.wav");
		createNote("c.wav");
		createNote("d.wav");
		createNote("e.wav");
		createNote("f.wav");
		createNote("g.wav");
		createNote("c5.wav");
		createNote("d5.wav");
		createNote("e5.wav");
		createNote("f5.wav");
		
		createBlackNote("c#.wav");
		createBlackNote("d#.wav");
		createBlackNote("f#.wav");
		createBlackNote("g#.wav");
		createBlackNote("a#.wav");
		createBlackNote("c5#.wav");
		createBlackNote("d5#.wav");
		
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				displayInfo(e);
			}

			/** Handle the key-pressed event from the text field. */
			public void keyPressed(KeyEvent e) {
				displayInfo(e);
				//System.out.println(e.getKeyChar());
				switch (e.getKeyCode()) {
					case KeyEvent.VK_H:
						myClips[0].start();
						myClips[0].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_J:
						myClips[1].start();
						myClips[1].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_A:
						myClips[2].start();
						myClips[2].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_S:
						myClips[3].start();
						myClips[3].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_D:
						myClips[4].start();
						myClips[4].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_F:
						myClips[5].start();
						myClips[5].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_G:
						myClips[6].start();
						myClips[6].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_K:
						myClips[7].start();
						myClips[7].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_L:
						myClips[8].start();
						myClips[8].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_SEMICOLON:
						myClips[9].start();
						myClips[9].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_QUOTE:
						myClips[10].start();
						myClips[10].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_W:
						myClipsBlack[0].start();
						myClipsBlack[0].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_E:
						myClipsBlack[1].start();
						myClipsBlack[1].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_T:
						myClipsBlack[2].start();
						myClipsBlack[2].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_Y:
						myClipsBlack[3].start();
						myClipsBlack[3].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_U:
						myClipsBlack[4].start();
						myClipsBlack[4].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_O:
						myClipsBlack[5].start();
						myClipsBlack[5].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_P:
						myClipsBlack[6].start();
						myClipsBlack[6].loop(Clip.LOOP_CONTINUOUSLY);
						break;
					case KeyEvent.VK_SHIFT:
						isShift = true;
						break;
				}
			}

			/** Handle the key-released event from the text field. */
			public void keyReleased(KeyEvent e) {
				displayInfo(e);
				switch (e.getKeyCode()) {
					case KeyEvent.VK_H:
						myClips[0].stop();
						break;
					case KeyEvent.VK_J:
						myClips[1].stop();
						break;
					case KeyEvent.VK_A:
						myClips[2].stop();
						break;
					case KeyEvent.VK_S:
						myClips[3].stop();
						break;
					case KeyEvent.VK_D:
						myClips[4].stop();
						break;
					case KeyEvent.VK_F:
						myClips[5].stop();
						break;
					case KeyEvent.VK_G:
						myClips[6].stop();
						break;
					case KeyEvent.VK_K:
						myClips[7].stop();
						break;
					case KeyEvent.VK_L:
						myClips[8].stop();
						break;
					case KeyEvent.VK_SEMICOLON:
						myClips[9].stop();
						break;
					case KeyEvent.VK_QUOTE:
						myClips[10].stop();
						break;
					case KeyEvent.VK_W:
						myClipsBlack[0].stop();
						break;
					case KeyEvent.VK_E:
						myClipsBlack[1].stop();
						break;
					case KeyEvent.VK_T:
						myClipsBlack[2].stop();
						break;
					case KeyEvent.VK_Y:
						myClipsBlack[3].stop();
						break;
					case KeyEvent.VK_U:
						myClipsBlack[4].stop();
						break;
					case KeyEvent.VK_O:
						myClipsBlack[5].stop();
						break;
					case KeyEvent.VK_P:
						myClipsBlack[6].stop();
						break;
					case KeyEvent.VK_SHIFT:
						isShift = true;
						break;
				}
			}
		});
		setFocusable(true);
	}
	
	public void createNote(String noteName) {
		try {
			File file = new File(noteName);
			if (file.exists()) {
				myClips[clipCount] = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
				myClips[clipCount].open(ais);
				clipCount++;
			}
			else {
				throw new RuntimeException("Sound: file not found.");
			}
		} catch (Exception e){ }
	}
	
	public void createBlackNote(String noteName) {
		try {
			File file = new File(noteName);
			if (file.exists()) {
				myClipsBlack[clipCountBlack] = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
				myClipsBlack[clipCountBlack].open(ais);
				clipCountBlack++;
			}
			else {
				throw new RuntimeException("Sound: file not found.");
			}
		} catch (Exception e){ }
	}
	
	public void displayInfo(KeyEvent e) {
		displayArea.append(e.getKeyChar() + newline);
	}
	
	private void play(SourceDataLine sourceLine, Note note, int ms) {
        ms = Math.min(ms, Note.SECONDS * 1000);
        int length = Note.SAMPLE_RATE * ms / 1000;
		int count = sourceLine.write(note.data(), 0, length);
    }
	
	public static void createAndShowGUI() {
		Piano frame = new Piano("Piano");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane();
        frame.pack();
        frame.setVisible(true);
	}
	
	public void addComponentsToPane(){

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(375, 125));
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}