/*
 * Zach Kuhns
 * ADSBInterface - This class takes hexadecimal input from an
 * onboard ADS-B module and parses the data for the NGATCAS software.
 */

import java.util.ArrayList;

public class ADSBInterface {
	Aircraft thisAircraft;
	ArrayList<Aircraft> otherAircraft;
	ArrayList<Position> position_pairs;
	
	public ADSBInterface(Aircraft thisAircraft, ArrayList<Aircraft> otherAircraft) {
		this.thisAircraft = thisAircraft;
		this.otherAircraft = otherAircraft;
		
		position_pairs = new ArrayList();
	}
	
	public void listen() {
		/*
		 * This method is left blank because
		 * we did not implement a system to simulate
		 * interrupts for data coming from an ADS-B
		 * module.  All tests will manually call
		 * the parseMessage method with hard coded
		 * ADS-B data.
		 */
	}
	
	/* 
	 * This method takes a character representing
	 * a hexadecimal character and returns its
	 * binary equivalent as a String.
	 */
	private String hex_to_bin(char hex_char) {
		switch(hex_char) {
			case '0': return "0000";
			case '1': return "0001";
			case '2': return "0010";
			case '3': return "0011";
			case '4': return "0100";
			case '5': return "0101";
			case '6': return "0110";
			case '7': return "0111";
			case '8': return "1000";
			case '9': return "1001";
			case 'a':
			case 'A': return "1010";
			case 'b':
			case 'B': return "1011";
			case 'c':
			case 'C': return "1100";
			case 'd':
			case 'D': return "1101";
			case 'e':
			case 'E': return "1110";
			case 'f':
			case 'F': return "1111";
		}
		
		return null;
	}
	
	/*
	 * Takes a String representing a binary number
	 * and converts it to its equivalent hexadecimal
	 * character.
	 */
	private char bin_to_hex(String bin_string) {
		switch (bin_string) {
			case "0000": return '0';
			case "0001": return '1';
			case "0010": return '2';
			case "0011": return '3';
			case "0100": return '4';
			case "0101": return '5';
			case "0110": return '6';
			case "0111": return '7';
			case "1000": return '8';
			case "1001": return '9';
			case "1010": return 'A';
			case "1011": return 'B';
			case "1100": return 'C';
			case "1101": return 'D';
			case "1110": return 'E';
			case "1111": return 'F';
		}
		
		return '#';
	}
	
	/*
	 * This method takes a String representing a 
	 * hexadecimal number and converts it to a String
	 * representing its binary equivalent.
	 */
	private String hex_to_bin(String hex_string) {
		String bin_packet = "";
		
		for (int i = 0; i < hex_string.length(); i++) {
			bin_packet += hex_to_bin(hex_string.charAt(i));
		}
		
		return bin_packet;
	}
	
	/* 
	 * This method takes a binary string
	 * of length 6 and returns its
	 * character equivalent.  This character
	 * table is unique to the ADS-B aircraft
	 * ID system.
	 */
	private char id_bin_to_char(String bin_string) {
		switch (bin_string) {
		case "000000" : return '#';
		case "000001" : return 'A';
		case "000010" : return 'B';
		case "000011" : return 'C';
		case "000100" : return 'D';
		case "000101" : return 'E';
		case "000110" : return 'F';
		case "000111" : return 'G';
		case "001000" : return 'H';
		case "001001" : return 'I';
		case "001010" : return 'J';
		case "001011" : return 'K';
		case "001100" : return 'L';
		case "001101" : return 'M';
		case "001110" : return 'N';
		case "001111" : return 'O';
		case "010000" : return 'P';
		case "010001" : return 'Q';
		case "010010" : return 'R';
		case "010011" : return 'S';
		case "010100" : return 'T';
		case "010101" : return 'U';
		case "010110" : return 'V';
		case "010111" : return 'W';
		case "011000" : return 'X';
		case "011001" : return 'Y';
		case "011010" : return 'Z';
		case "110000" : return '0';
		case "110001" : return '1';
		case "110010" : return '2';
		case "110011" : return '3';
		case "110100" : return '4';
		case "110101" : return '5';
		case "110110" : return '6';
		case "110111" : return '7';
		case "111000" : return '8';
		case "111001" : return '9';
		}
		
		return '#';
	}
	
	/*
	 * This method takes a 6 bit number
	 * and converts it to its equivalent
	 * character for an aircraft's id.
	 */
	private String getId(String bin_string) {
		String id = "";
		String token = "";
		
		for (int i = 0; i < 8; i++) {
			token = "";
			for (int j = i * 6; j < (i * 6) + 6; j++) {
				token += bin_string.charAt(j);
			}
			
			if (id_bin_to_char(token) == '#') {
				continue;
			}
			
			id += id_bin_to_char(token);
		}
		
		return id;
	}
	
	/*
	 * Precalculated latitude zones from
	 * https://github.com/junzis/py-adsb-decoder/blob/master/decoder.py
	 */
	private int getLatitudeZone(double latitude) {
		if (latitude < 0) {
			latitude = -latitude;
		}
		
		if (latitude < 10.47047130) return 59;
		if (latitude < 14.82817437) return 58;
		if (latitude < 18.18626357) return 57;
		if (latitude < 21.02939493) return 56;
		if (latitude < 23.54504487) return 55;
		if (latitude < 25.82924707) return 54;
		if (latitude < 27.93898710) return 53;
		if (latitude < 29.91135686) return 52;
		if (latitude < 31.77209708) return 51;
		if (latitude < 33.53993436) return 50;
		if (latitude < 35.22899598) return 49;
		if (latitude < 36.85025108) return 48;
		if (latitude < 38.41241892) return 47;
		if (latitude < 39.92256684) return 46;
		if (latitude < 41.38651832) return 45;
		if (latitude < 42.80914012) return 44;
		if (latitude < 44.19454951) return 43;
		if (latitude < 45.54626723) return 42;
		if (latitude < 46.86733252) return 41;
		if (latitude < 48.16039128) return 40;
		if (latitude < 49.42776439) return 39;
		if (latitude < 50.67150166) return 38;
		if (latitude < 51.89342469) return 37;
		if (latitude < 53.09516153) return 36;
		if (latitude < 54.27817472) return 35;
		if (latitude < 55.44378444) return 34;
		if (latitude < 56.59318756) return 33;
		if (latitude < 57.72747354) return 32;
		if (latitude < 58.84763776) return 31;
		if (latitude < 59.95459277) return 30;
		if (latitude < 61.04917774) return 29;
		if (latitude < 62.13216659) return 28;
		if (latitude < 63.20427479) return 27;
		if (latitude < 64.26616523) return 26;
		if (latitude < 65.31845310) return 25;
		if (latitude < 66.36171008) return 24;
		if (latitude < 67.39646774) return 23;
		if (latitude < 68.42322022) return 22;
		if (latitude < 69.44242631) return 21;
		if (latitude < 70.45451075) return 20;
		if (latitude < 71.45986473) return 19;
		if (latitude < 72.45884545) return 18;
		if (latitude < 73.45177442) return 17;
		if (latitude < 74.43893416) return 16;
		if (latitude < 75.42056257) return 15;
		if (latitude < 76.39684391) return 14;
		if (latitude < 77.36789461) return 13;
		if (latitude < 78.33374083) return 12;
		if (latitude < 79.29428225) return 11;
		if (latitude < 80.24923213) return 10;
		if (latitude < 81.19801349) return 9;
		if (latitude < 82.13956981) return 8;
		if (latitude < 83.07199445) return 7;
		if (latitude < 83.99173563) return 6;
		if (latitude < 84.89166191) return 5;
		if (latitude < 85.75541621) return 4;
		if (latitude < 86.53536998) return 3;
		if (latitude < 87.00000000) return 2;
		else {
			return 1;
		}
	}
	
	/*
	 * This method takes a binary String from the ADS-B
	 * data frame and calculates latitude
	 * from the data.
	 */
	private double[] calculateLatitude(int pair_index) {
		String latitude_even = "";
		String latitude_odd = "";
		for (int i = 14; i < 31; i++) {
			latitude_even += position_pairs.get(pair_index).even.charAt(i);
			latitude_odd += position_pairs.get(pair_index).odd.charAt(i);
		}
		
		double LAT_CPR_EVEN = (double)Integer.parseInt(latitude_even, 2) / 131072;
		double LAT_CPR_ODD = (double)Integer.parseInt(latitude_odd, 2) / 131072;
		
		int j = (int) Math.floor(59 * LAT_CPR_EVEN - 60 * LAT_CPR_ODD + 0.5);
		
		double DLat_EVEN = 360.0 / 60;
		double DLat_ODD  = 360.0 / 59;
		
		double even_latitude = DLat_EVEN * ((j % 60) + LAT_CPR_EVEN);
		if (even_latitude >= 270) {
			even_latitude -= 360;
		}
		
		double odd_latitude = DLat_ODD * ((j % 59) + LAT_CPR_ODD);
		if (odd_latitude >= 270) {
			odd_latitude -= 360;
		}
		
		if (getLatitudeZone(even_latitude) != getLatitudeZone(odd_latitude)) {
			return null;
		}
		
		double[] latitude = {even_latitude, odd_latitude};
		return latitude;
	}
	
	/*
	 * This method takes a binary String from the ADS-B
	 * data frame and calculates longitude
	 * from the data.
	 */
	private double calculateLongitude(int pair_index, double[] latitude) {
		double ni;
		double m;
		double longitude;
		
		String longitude_even = "";
		String longitude_odd = "";
		for (int i = 31; i < 48; i++) {
			longitude_even += position_pairs.get(pair_index).even.charAt(i);
			longitude_odd += position_pairs.get(pair_index).odd.charAt(i);
		}
		
		double LON_CPR_EVEN = (double)Integer.parseInt(longitude_even, 2) / 131072;
		double LON_CPR_ODD = (double)Integer.parseInt(longitude_odd, 2) / 131072;
		
		if (position_pairs.get(pair_index).even_time >= position_pairs.get(pair_index).odd_time) {
			ni = getLatitudeZone(latitude[0]);
			m = Math.floor(LON_CPR_EVEN * (getLatitudeZone(latitude[0])-1) - LON_CPR_ODD * getLatitudeZone(latitude[0]) + 0.5);
			longitude = 360.0 / ni * ((m % ni) + LON_CPR_EVEN);
		} else {
			ni = getLatitudeZone(latitude[1]) - 1;
			m = Math.floor(LON_CPR_EVEN * (getLatitudeZone(latitude[1])-1) - LON_CPR_ODD * getLatitudeZone(latitude[1]) + 0.5);
			longitude = 360.0 / ni * ((m % ni) + LON_CPR_ODD);
		}
		
		if (longitude >= 180) {
			longitude -= 360;
		}
		
		return longitude;
	}
	
	private double calculateAltitude(String bin_string) {
		if (bin_string.charAt(7) == '0') {
			return 1337;
		}
		
		String altitude = "";
		
		for (int i = 0; i < 12; i++) {
			if (i == 7) {
				continue;
			}
			altitude += bin_string.charAt(i);
		}
		
		double N = (double)Integer.parseInt(altitude, 2);
		
		return N * 25 - 1000;
	}
	
	/*
	 * This method takes a String representing
	 * a binary number and breaks it into
	 * ADS-B positional data.
	 */
	private double[] getPosition(String ICAO24, String bin_string) {
		int pair_index = 0; // Index to remove the pair if needed
		boolean pair_exists = false; // Boolean to determine if we found a pair
		
		for (int i = 0; i < position_pairs.size(); i++) {
			if (position_pairs.get(i).ICAO24.equals(ICAO24)) {
				pair_index = i;
				pair_exists = true;
				
				if (bin_string.charAt(13) == '0') {
					position_pairs.get(i).even = bin_string;
					position_pairs.get(i).even_time = System.currentTimeMillis();
				} else {
					position_pairs.get(i).odd = bin_string;
					position_pairs.get(i).odd_time = System.currentTimeMillis();
				}
				
				if (position_pairs.get(i).even == null || position_pairs.get(i).odd == null) {
					 // Do not attempt the position calculation while any pieces are missing.
					break;
				}
			}
		}
		
		// If the pair does not exist add it to the pair_list
		if (!pair_exists) {
			if (bin_string.charAt(13) == '0') {
				position_pairs.add(new Position(ICAO24, bin_string, null));
				position_pairs.get(position_pairs.size()-1).even_time = System.currentTimeMillis();
			} else {
				position_pairs.add(new Position(ICAO24, null, bin_string));
				position_pairs.get(position_pairs.size()-1).odd_time = System.currentTimeMillis();
			}
			return null;
		}
		
		double[] latitude = calculateLatitude(pair_index);
		if (latitude == null) {
			return null;
		}
		
		double longitude = calculateLongitude(pair_index, latitude);
		if (longitude == 1337) {
			return null;
		}
		
		double altitude = calculateAltitude(bin_string);
		if (altitude == 1337) {
			return null;
		}
		
		if (position_pairs.get(pair_index).even_time >= position_pairs.get(pair_index).odd_time) {
			double[] position = new double[3];
			position[0] = latitude[0];
			position[1] = longitude;
			position[2] = altitude;
			position_pairs.remove(pair_index);
			return position;
		} else {
			double[] position = new double[3];
			position[0] = latitude[1];
			position[1] = longitude;
			position[2] = altitude;
			position_pairs.remove(pair_index);
			return position;
		}
	}
	
	/*
	 * This method takes a String representing a
	 * binary number and breaks it into 
	 * ADS-B heading data.
	 */
	private double[] getHeading(String ICAO24, String bin_string) {
		int east_west_velocity = 0;
		String EW = "" + bin_string.charAt(5);
		
		String EWV = "";
		for (int i = 6; i < 16; i++) {
			EWV += bin_string.charAt(i);
		}
		
		switch (EW) {
			case "0" : {
				east_west_velocity = Integer.parseInt(EWV, 2);
			}
			case "1" : {
				east_west_velocity = -Integer.parseInt(EWV, 2);
			}
		}
		
		int north_south_velocity = 0;
		String NS = "" + bin_string.charAt(16);
		
		String NSV = "";
		for (int i = 17; i < 27; i++) {
			NSV += bin_string.charAt(i);
		}
		
		switch (NS) {
			case "0" : {
			north_south_velocity = Integer.parseInt(NSV, 2);
			}
			case "1" : {
			north_south_velocity = -Integer.parseInt(NSV, 2);
			}
		}
		
		int vertical_velocity = 0;
		String V = "" + bin_string.charAt(36);
		
		String VV = "";
		for (int i = 27; i < 36; i++) {
			VV += bin_string.charAt(i);
		}
		
		switch (VV) {
			case "0" : {
				vertical_velocity = Integer.parseInt(VV, 2);
			}
			case "1" : {
				vertical_velocity = -Integer.parseInt(VV, 2);
			}
		}
		
		double[] velocity = new double[3];
		velocity[0] = east_west_velocity;
		velocity[1] = north_south_velocity;
		velocity[2] = vertical_velocity;
		
		return velocity;
	}
	
	/* 
	 * This method handles parsing
	 * the message received from the ADS-B
	 * interface.
	 */
	private void parseMessage(String message) {
		if (message.length() != 28) {
			return;
		}
		
		message = hex_to_bin(message);
		
		String DF = "";
		for (int i = 0; i < 5; i++) {
			DF += message.charAt(i);
		}
		
		switch(DF) {
			case "10001" : break;
			default : return;
		}
		
		String ICAO24 = "";
		for (int i = 8; i < 32; i+=4) {
			String bin_char = "";
			for (int j = i; j < i+4; j++) {
				bin_char += message.charAt(j);
			}
			ICAO24 += bin_to_hex(bin_char);
		}
		
		String TC = "";
		for (int i = 32; i < 37; i++ ) {
			TC += message.charAt(i);
		}
		
		String DATA = "";
		for (int i = 40; i < 88; i++) {
			DATA += message.charAt(i);
		}
		
		switch (TC) {
			case "00001" :
			case "00010" :
			case "00011" :
			case "00100" : addAircraft(ICAO24, getId(DATA)); return;
			case "00101" :
			case "00110" :
			case "00111" :
			case "01000" :
			case "01001" :
			case "01010" :
			case "01011" :
			case "01100" :
			case "01101" :
			case "01110" :
			case "01111" :
			case "10000" :
			case "10001" :
			case "10010" : {
				double[] position = getPosition(ICAO24, DATA);
				if (position == null) {
					return;
				}
				addPosition(ICAO24, position);
				return;
			}
			case "10011" : {
				double[] heading = getHeading(ICAO24, DATA);
				if (heading == null) {
					return;
				}
				addHeading(ICAO24, heading);
				return;
			}
			default : return;
		}
	}
	
	/*
	 * Attempts to add an aircraft with a new unique
	 * ICAO24.  If the plane already exists return.
	 */
	public void addAircraft(String ICAO24, String message) {
		if (thisAircraft.getId() == ICAO24) {
			return;
		}
		
		for (int i = 0; i < otherAircraft.size(); i++) {
			if (otherAircraft.get(i).getId().equals(ICAO24)) {
				return;
			}
		}
		
		otherAircraft.add(new Aircraft(ICAO24, null, null, 0));
	}
	
	/*
	 * Attempts to add an aircraft's position
	 * from ADS-B binary data
	 */
	public void addPosition(String ICAO24, double[] position) {
		if (thisAircraft.getId() == ICAO24) {
			thisAircraft = new Aircraft(ICAO24, position, thisAircraft.getHeading(), 0);
		}
		for (int i = 0; i < otherAircraft.size(); i++) {
			if (otherAircraft.get(i).getId().equals(ICAO24)) {
				otherAircraft.add(new Aircraft(
						ICAO24,
						position,
						otherAircraft.get(i).getHeading(),
						0));
				
				otherAircraft.remove(i);
				return;
			}
		}
		
		otherAircraft.add(new Aircraft(ICAO24, position, null, 0));
	}
	
	/*
	 * Attempts to add an aircraft's heading
	 * from ADS-B binary data
	 */
	public void addHeading(String ICAO24, double[] heading) {
		if (thisAircraft.getId() == ICAO24) {
			thisAircraft = new Aircraft(ICAO24, thisAircraft.getPosition(), heading, 0);
		}
		for (int i = 0; i < otherAircraft.size(); i++) {
			if (otherAircraft.get(i).getId().equals(ICAO24)) {
				otherAircraft.add(new Aircraft(
						ICAO24,
						otherAircraft.get(i).getPosition(),
						heading,
						0));
				
				otherAircraft.remove(i);
				return;
			}
		}
		
		otherAircraft.add(new Aircraft(ICAO24, null, heading, 0));
	}
	
	/*
	 * ADS-B latitude and longitude
	 * calculations require two different inputs to compare
	 * for accuracy.  This class stores two position
	 * binary Strings to run these calculations for
	 * finding aircraft locations.
	 */
	private class Position {
		String ICAO24;
		long even_time;
		String even;
		long odd_time;
		String odd;
		
		public Position(String ICAO24, String even, String odd) {
			this.ICAO24 = ICAO24;
			this.even = even;
			this.odd = odd;
		}
	}
}
