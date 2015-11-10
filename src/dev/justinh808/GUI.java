package dev.justinh808;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;
import dev.justinh808.FileOperationCancelledException;

/**
 * @author Justin Ho
 *
 */
public class GUI
{

    private static JFrame window;
    private static JTextArea ta;

    /**
     * Constructs a new GUI with the given title and default width and height.
     * 
     * @param title
     *            -The title to display on the window.
     */
    public GUI(String title)
    {
        this(title, 600, 600, Color.BLACK);
    }

    /**
     * Constructs a new GUI with the given title, width, and height. Black colored background by default.
     * 
     * @param title
     *            -The title to display on the window.
     * @param width
     *            -The width of the window.
     * @param height
     *            -The height of the window.
     */
    public GUI(String title, int width, int height)
    {
        this(title, width, height, Color.BLACK);
    }

    /**
     * Constructs a new GUI with the given title, background Color, width, and height.
     * 
     * @param title
     *            -The title to display on the window.
     * @param width
     *            -The width of the window.
     * @param height
     *            -The height of the window.
     */
    public GUI(String title, int width, int height, Color col)
    {
        try
        {
            if (System.getProperty("os.name").toLowerCase().contains("windows"))
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        }
        catch (Exception e)
        {
            showException(e);
        }
        window = new JFrame(title);
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
        window.setBackground(col);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Adds this component to the GUI window.
     * 
     * @param comp
     *            The component to add to the window.
     * @return true if and only if the component was added to the window.
     */
    public boolean add(Component comp)
    {
        return window.add(comp) != null;
    }

    /**
     * Adds a scrollable text area to the window. Default width and height of 550.
     * 
     * @return true if and only if the scrollable text area is added to the window.
     */
    public boolean addScrollableTextArea()
    {
        return addScrollableTextArea(550, 550, Color.WHITE, Color.BLACK);
    }

    /**
     * Adds a scrollable text area to the window with the given width and height.
     * 
     * @return true if and only if the scrollable text area is added to the window.
     */
    public boolean addScrollableTextArea(int width, int height)
    {
        return addScrollableTextArea(width, height, Color.WHITE, Color.BLACK);
    }

    /**
     * Adds a scrollable text area to the window. With the given parameters
     * 
     * @param width
     *            the width of the scrollable text area
     * @param height
     *            the height of the scrollable text area
     * @param fg
     *            the foreground color of the text area
     * @param bg
     *            the background color of the text area
     * @return true if and only if the scrollable text area is added to the window.
     */
    public boolean addScrollableTextArea(int width, int height, Color fg, Color bg)
    {
        ta = new JTextArea();
        // Text Area Set up
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setSize(width, height);
        ta.setEditable(false);
        ta.setFont(new Font("my font", Font.TRUETYPE_FONT, 16));
        ta.setBackground(bg);
        ta.setForeground(fg);
        // Auto Scroll
        DefaultCaret caret = (DefaultCaret) ta.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        return window.add(new JScrollPane(ta)) != null;
    }

    /**
     * Prints the text to the window with a new line.
     * 
     * @param text
     *            -The text to print to the window.
     */
    public void println(String text)
    {
        ta.append(text + "\n");
    }

    /**
     * Prints the object to the window with a new line.
     * 
     * @param text
     *            -The object to print to the window.
     */
    public void println(Object text)
    {
        ta.append(text.toString() + "\n");
    }

    /**
     * Prints a new line to the window.
     */
    public void println()
    {
        ta.append("\n");
    }

    /**
     * Prints the text to the window with no new line.
     * 
     * @param text
     *            -The text to print to the window.
     */
    public void print(String text)
    {
        ta.append(text);
    }

    /**
     * Prints the text to the window with no new line.
     * 
     * @param text
     *            -The text to print to the window in Object form.
     */
    public void print(Object text)
    {
        ta.append(text.toString());
    }

    /**
     * Shows the window.
     */
    public void showWindow()
    {
        window.setVisible(true);
    }

    /**
     * Shows the given exception in a new window.
     * 
     * @param ex
     *            -The exception in string form.
     */
    public static void showException(Exception ex)
    {
        window = new JFrame("Exception");
        window.setSize(500, 200);
        window.setLocationRelativeTo(null);
        JLabel exception = new JLabel(ex.toString(), JLabel.CENTER);
        window.add(exception);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Shows the given message in a new window.
     * 
     * @param msg
     *            -The message in string form.
     */
    public static void showMessage(String msg)
    {
        window = new JFrame("Message");
        window.setSize(500, 200);
        window.setLocationRelativeTo(null);
        JLabel message = new JLabel(msg, JLabel.CENTER);
        window.add(message);
        window.setVisible(true);
    }

    /**
     * Gets the file (using JfileChooser) needed for input.
     * 
     * @return The file chosen -null if the user presses cancel
     * @throws FileOperationCancelledException
     *             File operation cancelled.
     */

    public static File getFile(String title) throws FileOperationCancelledException
    {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(title);
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int response = fc.showDialog(window, "use");
        if (response == JFileChooser.APPROVE_OPTION)
        {
            return fc.getSelectedFile();
        }
        else
        {
            throw new FileOperationCancelledException("File operation cancelled");
        }

    }

    /**
     * Gets the file (using the JFileChooser) needed for input.
     * 
     * @return The file the user chooses.
     * @throws FileOperationCancelledException
     *             File operation cancelled.
     */

    public static File getFolder(String title) throws FileOperationCancelledException
    {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(title);
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int response = fc.showDialog(window, "use");
        if (response == JFileChooser.APPROVE_OPTION)
        {
            return fc.getSelectedFile();
        }
        else
        {
            throw new FileOperationCancelledException("File operation cancelled");
        }

    }

}
