package dev.justinh808;

/**
 * @author Justin Ho
 *
 */
public class FileOperationCancelledException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a default FileOperationCancelledException.
     */
    public FileOperationCancelledException()
    {
        super();
    }

    /**
     * Creates a FileOperationCancelledException with this message.
     * 
     * @param message
     *            The message to show.
     */
    public FileOperationCancelledException(String message)
    {
        super(message);
    }
}
