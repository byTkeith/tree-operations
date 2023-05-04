//package src;

// Used to signal violations of preconditions for
// various shortest path algorithms.
class GraphException extends RuntimeException
{
    /**
	 * a static method
	 */
	private static final long serialVersionUID = 1L;
/**
method graph exception that takes in string
 */
	public GraphException( String name )
    {
        super( name );
    }
}
