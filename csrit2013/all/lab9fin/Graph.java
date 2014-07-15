

interface Graph
{
    /*
        Add "source" and "destination" nodes to the graph. Note that
        this is an undirected graph, so add the nodes in both directions.
    */
    public void add( String key, String value ) ;

    /*
        Find a path from "source" to "destination". Returns a comma 
        separated String listing each of the nodes in the path.
    */
    public String findPath( String start, String finish ) ;

    /*
        Converts a graph to a string representation consisting of the node,
        followed by a ':' followed by a ' ' followed by a comma separated
        list of neighbors.
    */
    public String toString( ) ;

    /*
        Routine to find a path from "node" to "finish". The visited list
        keeps track of the nodes in the path. Returns true if a path is
        found, false otherwise. Note that it is commented out, here, 
        because it doesn't really belong in the interface.
    */
//    private Boolean visitDFS( String node, List<String> visited, String finish ) ;

    /*
        Default constructor for the DFSGraph. Should initialize the 
        data structure that is being used to represent the graph. Note
        that it is commented out, here, because it doesn't really belong
        in the interface.
    */
//    public DFSGraph( ) ;

}