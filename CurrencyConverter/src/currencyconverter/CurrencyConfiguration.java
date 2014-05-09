/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package currencyconverter;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
//import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

/**
 *
 * @author Cemil
 */
public class CurrencyConfiguration {
    
    private ArrayList<String> units;
    private ArrayList<String> names;
    // Json Reader

    /**
     * @param args the command line arguments
     */
    
    
    public  CurrencyConfiguration(String file) {
     
        JSONParser parser = new JSONParser();
        units = new ArrayList<>();
        names = new ArrayList<>();
        
         try {  
                JSONObject jsonObject;
                InputStream is = this.getClass().getResourceAsStream("/"+file);
                jsonObject = (JSONObject) parser.parse(getStringFromInputStream(is));
                //jsonObject = (JSONObject) parser.parse(is);
		//Object obj = parser.parse(new FileReader("cc_test.json"));
 	        //JSONObject jsonObject = (JSONObject) obj;
                JSONArray junits = (JSONArray) jsonObject.get("units");
                Iterator<JSONObject> iterator = junits.iterator();
                 
                while (iterator.hasNext()) {
                    
                    JSONObject all = iterator.next();
                    String country = (String) all.get("CountryCurrency");
                    String unit = (String) all.get("Units");
                    units.add(unit);
                    names.add(country);
                    
                }
         }
               catch ( Exception e) {
                   
                    e.printStackTrace();
                    
               }
     }
    
    //Get Units
     public String[] getUnits() {
         
        String tmp[] = new String[units.size()];
        tmp = units.toArray(tmp);
        return tmp;
        
     }
     
     //Get Countries
     public String[] getCountries() {
         
        String tmp[] = new String[names.size()];
        tmp = names.toArray(tmp);
        return tmp;
        
    }
     
     private static String getStringFromInputStream(InputStream is) {
         
        BufferedReader buffread;
        StringBuilder strbuild = new StringBuilder();
        String line;
        
        try {

            buffread = new BufferedReader(new InputStreamReader(is));
            while ((line = buffread.readLine()) != null) {
                strbuild.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
                
        }
        return strbuild.toString();
     }
}
   
