package test;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.yeep.basis.swing.widget.font.FontWidget;

@SuppressWarnings("serial")
public class Test extends JFrame
{
	private JButton okBtn = new JButton("OK");
	private FontWidget fw;

	public Test()
	{
		super("Rearrangeable Tree");
		setSize(300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		fw = new FontWidget(new Font("Default", Font.PLAIN, 15));
		this.okBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Font = " + fw.getSelectedFont());
			}
		});

		panel.add(fw);
		panel.add(this.okBtn);
		getContentPane().add(panel);

		getContentPane().setPreferredSize(new Dimension(900, 500));
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new Test();
	}
}
