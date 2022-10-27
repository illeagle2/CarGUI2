// Car GUIv2
// Author: Matthew Davis
// Lab 10-11
// Using code from T Fallon's Lab 6-7 listDiff.java


//Packages used
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CarGUIv2 extends JFrame implements ActionListener, AdjustmentListener
{

	 // Global Variable Declarations
	 // create three textfields for name,type,year.

	 private JTextField nameField = new JTextField(25);
	 private JTextField typeField = new JTextField(25);
	 private JTextField yearField = new JTextField(25);

	// Variables used for textarea, scrollbar, and button
	 private JScrollPane displayScrollPane;
	 private JTextArea display = new JTextArea(10,20);

	 private JLabel speedLabel = new JLabel("Speed");
	 private JScrollBar speedScrollBar = new JScrollBar(JScrollBar.VERTICAL,0,1,0,100);

	 private JButton createButton = new JButton("Create");
	 private JButton playbackButton = new JButton("Playback");

	 // declare our BufferedReaders for reading the two input files
	 //private BufferedReader inFirst,inSecond;

	 private BufferedReader inFirst;

	 // declare our FileWriter, which will create our car file
	 private FileWriter playbackWriter;



	// Construstor
	public CarGUIv2()
	{
		 super("Car GUIv2");
		 getContentPane().setLayout( new BorderLayout() );

		 // add the three labels and textfields north
		 JPanel inputPanel = new JPanel(new GridLayout(2,3));
		 inputPanel.add(new JLabel("Name"));
		 inputPanel.add(new JLabel("Type"));
		 inputPanel.add(new JLabel("Year"));
		 inputPanel.add(nameField);
		 inputPanel.add(typeField);
		 inputPanel.add(yearField);

		 getContentPane().add(inputPanel,"North");

		 // create and populate east side with label,scrollbar,button.
		 JPanel eastPanel = new JPanel(new GridLayout(4,1,1,1));

		 eastPanel.add(speedLabel);
		 eastPanel.add(speedScrollBar);
		 eastPanel.add(createButton);
		 eastPanel.add(playbackButton);

		 getContentPane().add(eastPanel,"East");

		 // Add listener for our Buttons.
		 createButton.addActionListener(this);
		 playbackButton.addActionListener(this);

		 // configure our ScrollPane to contain our display
		 displayScrollPane = new JScrollPane(display);
		 getContentPane().add(displayScrollPane, "Center");
		 display.setLineWrap( true );
		 display.setEditable( false );


	} // end CarGUIv2()

	public void actionPerformed(ActionEvent ae)
	{

		// code preformed if createButton is pressed.
		if (ae.getSource() == createButton)
		{
			try
			{
				playbackWriter = new FileWriter("car.dat");
			}
			catch (IOException ioe)
			{
			}
			Carv2 c = new Carv2();
			c.setName(nameField.getText());
			c.setType(typeField.getText());
			c.setYear(yearField.getText());
			c.setSpeed(speedScrollBar.getValue());
			display.append(c.getMessage());

			nameField.setText("");
			typeField.setText("");
			yearField.setText("");
			try
			{
				playbackWriter.write(display.getText());
				playbackWriter.close();
			}
			catch (IOException ioe)
			{
			}

		}

		else if(ae.getSource() == playbackButton)
		{
			//code preformed if playback button pressed.
			try
			{
				inFirst = new BufferedReader(new FileReader("car.dat"));
				String lineFirst = inFirst.readLine();

				while (lineFirst != null)
				{
					display.append(lineFirst);
					lineFirst = inFirst.readLine();

				}


			}
			catch (IOException ioe)
			{
				display.setText(ioe.getMessage());
			}
		}

	} // actionPerformed()


	public void adjustmentValueChanged(AdjustmentEvent e)
	{
	}//adjustmmentValueChanged()


	public static void main(String args[])
	{
		 CarGUIv2 f = new CarGUIv2();
		 f.setSize(490, 300);
		 f.setVisible(true);
		 f.addWindowListener(new WindowAdapter()
		 { // Quit the application
			 public void windowClosing(WindowEvent e)
			 {
			 	System.exit(0);
			 }
		  });
	 } // main()
} // end of class