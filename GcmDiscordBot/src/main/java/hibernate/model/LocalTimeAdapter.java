package hibernate.model;

import java.time.LocalTime;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter (for JAXB) to convert between the LocalTime and the ISO 8601 String
 * representation of the time such as '22:58:00'.
 * 
 * @author Lyan Dwi Pangestu
 */
public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

	@Override
	public LocalTime unmarshal(String v) throws Exception {
		return LocalTime.parse(v);
	}

	@Override
	public String marshal(LocalTime v) throws Exception {
		return v.toString();
	}

}