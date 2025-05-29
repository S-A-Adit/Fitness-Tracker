package gui_version;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FitnessTrackerGUI extends JFrame {
    private final CreateTable createTable;
    private final PrintTable PrintTable;
    private final JTextArea outputTextArea;

    public FitnessTrackerGUI() {
        setTitle("Fitness Tracker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createTable = new CreateTable();
        PrintTable = new PrintTable();

        JLabel welcomeLabel = new JLabel("Welcome to the Fitness Tracker!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton createTableButton = new JButton("1. Create Table");
        JButton addRowButton = new JButton("2. Add Rows");
        JButton runQueryButton = new JButton("3. Run Query");
        JButton updateRowButton = new JButton("4. Update Row");
        JButton deleteRowButton = new JButton("5. Delete Row");
        JButton dropTableButton = new JButton("6. Drop Table");

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        buttonPanel.add(createTableButton);
        buttonPanel.add(addRowButton);
        buttonPanel.add(runQueryButton);
        buttonPanel.add(updateRowButton);
        buttonPanel.add(deleteRowButton);
        buttonPanel.add(dropTableButton);

        // Output Area
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // Action Listeners
        createTableButton.addActionListener(e -> {
            createTable.createTable();
            String tableContent = PrintTable.getTableAsString();  // <-- You will add this method below
            outputTextArea.setText(tableContent);
        });

        addRowButton.addActionListener(e -> {
            new InsertInto().insertInto();
            outputTextArea.setText(PrintTable.getTableAsString());
        });

        runQueryButton.addActionListener(e -> {
            Query query = new Query();
            query.showAgeRange(outputTextArea);
            query.showAverageCount(outputTextArea);
        });


        updateRowButton.addActionListener(e -> {
            new UpdateRow().updateRow();
            outputTextArea.setText(PrintTable.getTableAsString());
        });

        deleteRowButton.addActionListener(e -> {
            new DeleteRow().deleteRow();
            outputTextArea.setText(PrintTable.getTableAsString());
        });

        dropTableButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to drop the table? This action cannot be undone.",
                    "Confirm Drop",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                new DropTable().dropTable();
                outputTextArea.setText("Table dropped. No data available.");
            }
        });

        // Layout
        setLayout(new BorderLayout(10, 10));
        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);  // <-- Add scrollable text area
    }
    
public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {FitnessTrackerGUI gui = new FitnessTrackerGUI(); gui.setVisible(true);});
	 }
}


