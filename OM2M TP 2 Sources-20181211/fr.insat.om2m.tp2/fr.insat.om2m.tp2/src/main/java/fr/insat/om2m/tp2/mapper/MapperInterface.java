package fr.insat.om2m.tp2.mapper;
/**
 * Interface for the mapping operations
 *
 */
public interface MapperInterface {
	/**
	 * Marshal operation object to string (xml)
	 * @param obj
	 * @return xml representation
	 */
	public String marshal(Object obj);

	/**
	 * Unmarshal operation string (xml) to object. Be sure to check the class 
	 * of the object before using it.
	 * @param representation (xml)
	 * @return java object
	 */
	public Object unmarshal(String representation);
}
