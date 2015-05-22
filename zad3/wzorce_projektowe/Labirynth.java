/**
 * Labirynt - przykład komunikacji 
 * między obiektami z użyciem wzorca 
 * mediatora (mediator design pattern)
 *  
 * @author Jakub Kuryłowicz
 */


package wzorce_projektowe;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

interface Movement {
	public void go();
}

interface Master {
	
	public void setNorth(North n);
	public void setSouth(South s);
	public void setEast(East e);
	public void setWest(West w);
	
	public void goNorth();
	public void goSouth();
	public void goEast();
	public void goWest();
	
	public void updateDirections();
	
}

class North extends JButton implements Movement {
	
	private static final long serialVersionUID = 1L;
	private Master master;

	public North(ActionListener al, Master master) {
		super("North");
		this.addActionListener(al);
		this.master = master;
		this.master.setNorth(this);
	}
	
	@Override
	public void go() {
		this.master.goNorth();
	}
	
}

class South extends JButton implements Movement {
	
	private static final long serialVersionUID = 1L;
	private Master master;

	public South(ActionListener al, Master master) {
		super("South");
		this.addActionListener(al);
		this.master = master;
		this.master.setSouth(this);
	}
	
	@Override
	public void go() {
		this.master.goSouth();
	}
	
}

class East extends JButton implements Movement {
	
	private static final long serialVersionUID = 1L;
	private Master master;

	public East(ActionListener al, Master master) {
		super("East");
		this.addActionListener(al);
		this.master = master;
		this.master.setEast(this);
	}
	
	@Override
	public void go() {
		this.master.goEast();
	}
	
}

class West extends JButton implements Movement {
	
	private static final long serialVersionUID = 1L;
	private Master master;

	public West(ActionListener al, Master master) {
		super("West");
		this.addActionListener(al);
		this.master = master;
		this.master.setWest(this);
	}
	
	@Override
	public void go() {
		this.master.goWest();
	}
	
}

class Wizzard implements Master {
	
	private int x = 2;
	private int y = 2;
	private short[][] level = {
			{0x3, 0x8, 0x3, 0xd, 0x8},
			{0xf, 0x4, 0xf, 0x3, 0x5},
			{0xf, 0xa, 0x2, 0xc, 0xf},
			{0x6, 0xd, 0xe, 0x5, 0xf},
			{0xb, 0x7, 0xd, 0xc, 0xb},
		};
	
	/**
	 * number - walls
	 * 
	 * 0 - nsew    8 - nse
	 * 1 - n       9 - e
	 * 2 - (empty) a - sw
	 * 3 - nw      b - sew
	 * 4 - new     c - se
	 * 5 - ne      d - ns
	 * 6 - w       e - s
	 * 7 - nsw     f - ew
	 */
	
	private North n;
	private South s;
	private East e;
	private West w;
	
	@Override
	public void setNorth(North n) {
		this.n = n;
	}

	@Override
	public void setSouth(South s) {
		this.s = s;
	}

	@Override
	public void setEast(East e) {
		this.e = e;
	}

	@Override
	public void setWest(West w) {
		this.w = w;
	}

	@Override
	public void goNorth() {
		this.y --;
		this.updateDirections();
	}

	@Override
	public void goSouth() {
		this.y ++;
		this.updateDirections();
	}

	@Override
	public void goEast() {
		this.x ++;
		this.updateDirections();
	}

	@Override
	public void goWest() {
		this.x --;
		this.updateDirections();
	}
	
	@Override
	public void updateDirections() {
		
		this.n.setEnabled(false);
		this.s.setEnabled(false);
		this.e.setEnabled(false);
		this.w.setEnabled(false);
		
		switch (level[this.y][this.x]) {
			case 0x2:
			case 0x6:
			case 0x9:
			case 0xa:
			case 0xb:
			case 0xc:
			case 0xe:
			case 0xf:
				this.n.setEnabled(true);
				break;
		}
		
		switch (level[this.y][this.x]) {	
			case 0x1:
			case 0x2:
			case 0x3:
			case 0x4:
			case 0x5:
			case 0x6:
			case 0x9:
			case 0xf:
				this.s.setEnabled(true);
				break;
		}
		
		switch (level[this.y][this.x]) {
			case 0x1:
			case 0x2:
			case 0x3:
			case 0x6:
			case 0x7:
			case 0xa:
			case 0xd:
			case 0xe:
				this.e.setEnabled(true);
				break;
		}
		
		switch (level[this.y][this.x]) {		
			case 0x1:
			case 0x2:
			case 0x5:
			case 0x8:
			case 0x9:
			case 0xc:
			case 0xd:
			case 0xe:
				this.w.setEnabled(true);
				break;
		}
		
	}
	
}

public class Labirynth extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	Master master = new Wizzard();
	
	public Labirynth() {
		
		JPanel p = new JPanel(new GridLayout(3, 3));
		p.add(new JButton(" "));
		p.add(new North(this, master));
		p.add(new JButton(" "));
		p.add(new West(this, master));
		p.add(new JButton(" "));
		p.add(new East(this, master));
		p.add(new JButton(" "));
		p.add(new South(this, master));
		p.add(new JButton(" "));
        
		this.getContentPane().add(p, "North");
		
        setSize(400, 200);
        setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
        Movement direction = (Movement) ae.getSource();
        direction.go();
    }

	public static void main(String[] args) {
		new Labirynth();
	}

}

/*************************
 *        **             *
 *   *******   *********** 
 *   **   **   **        *
 *   **   **   **   **   * 
 *   **      x      **   *
 *   *******   *******   *
 *                  **   *
 *   ************   **   *
 *   **             **   *
 *************************/
