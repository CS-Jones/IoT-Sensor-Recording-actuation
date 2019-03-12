import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phidget22.*;








public class SensorToServer  {
    
    VoltageRatioInput slider = new VoltageRatioInput();
    VoltageRatioInput rotation = new VoltageRatioInput();

    
   RCServo servo = new RCServo();
    
	
	// static RCServo ch;  - Old way of doing servo 
    int lastSensorValue = 0;

    
    // address of server which will receive sensor data
    public static String sensorServerURL = "http://localhost:8080/MobileAssignment-Server/sensorToDB";
     public static void main(String[] args) throws Exception {

        new SensorToServer();

    }

    public SensorToServer() throws PhidgetException {
    	
        // This is the id of your PhidgetInterfaceKit (on back of device)
        slider.setDeviceSerialNumber(319851);
        // This is the channel your slider is connected to on the interface kit
        slider.setChannel(0);
        slider.open(5000);
       
        slider.addVoltageRatioChangeListener(new VoltageRatioInputVoltageRatioChangeListener() {
  			public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
  				double sensorReading = e.getVoltageRatio();
  				//System.out.printf("Slider Voltage Ratio Changed: %.3g\n", sensorReading);
  				int scaledSensorReading = (int) (1000 * sensorReading);
  				// send value to server if changed since last reading
  				if (scaledSensorReading != lastSensorValue ) {
  					System.out.println("Sending new sensor value : " + scaledSensorReading);
  					sendToServer("Slider",+scaledSensorReading);
  					lastSensorValue = scaledSensorReading;
  				}
  			}
         });
        
       // This is the id of your PhidgetInterfaceKit (on back of device)
       rotation.setDeviceSerialNumber(319851);
      //  This is the channel your slider is connected to on the interface kit
       rotation.setChannel(1);
       rotation.open(5000);
        
        
        
        rotation.addVoltageRatioChangeListener(new VoltageRatioInputVoltageRatioChangeListener() {
  			public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
  				double sensorReading = e.getVoltageRatio();
  				//System.out.printf("Slider Voltage Ratio Changed: %.3g\n", sensorReading);
  				int scaledSensorReading = (int) (1000 * sensorReading);
  				// send value to server if changed since last reading
  				if (scaledSensorReading != lastSensorValue ) {
  					System.out.println("Sending new sensor value : " + scaledSensorReading);
  					sendToServer("Rotation", + scaledSensorReading);
  					lastSensorValue = scaledSensorReading;
  				}
  			}
         });
        
        
   //     System.out.println("Opening and waiting 5 seconds for servo attachment...");
        servo.open(5000);
        servo.setTargetPosition(0);;
        servo.setEngaged(true);

        slider.addVoltageRatioChangeListener(new VoltageRatioInputVoltageRatioChangeListener() {
			public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
				// System.out.printf("Slider Voltage Ratio Changed: %.3g\n", e.getVoltageRatio());
				try {
					// scale sensor value from 0-1 to 0-180
					servo.setTargetPosition(e.getVoltageRatio()*180.0);
				} catch (PhidgetException e1) {
					e1.printStackTrace();
				}
			}
       });
        
 
        
        /*      ch = new RCServo(); *********************- Old way of doing servo *******************  
        
        ch.addVelocityChangeListener(new RCServoVelocityChangeListener() {
			public void onVelocityChange(RCServoVelocityChangeEvent e) {
				System.out.printf("Velocity Changed: %.3g\n", e.getVelocity());
			}
        });
        
        ch.addPositionChangeListener(new RCServoPositionChangeListener() {
			public void onPositionChange(RCServoPositionChangeEvent e) {
				System.out.printf("Position Changed: %.3g\n", e.getPosition());
			}
        });
        
        ch.addTargetPositionReachedListener(new RCServoTargetPositionReachedListener() {
			public void onTargetPositionReached(RCServoTargetPositionReachedEvent e) {
				System.out.printf("Target Position Reached: %.3g\n", e.getPosition());
			}
        });
        
        try {
          

            System.out.println("Opening and waiting 5 seconds for attachment...");
            ch.open(5000);

            System.out.println("Setting target position to 90");
            ch.setTargetPosition(90.0);
            System.out.println("Setting engaged");
            ch.setEngaged(true);
       
            ch.setTargetPostion(val);
            Thread.sleep(5000);

            ch.close();
            System.out.println("\nClosed Motor Servo");
            
        } catch (PhidgetException ex) {
            System.out.println(ex.getDescription());
        }
        
        
    *************************************End of Code of old way to do servo - Explained in Report ************************************    */ 
        
        
        
     	servo.addVelocityChangeListener(new RCServoVelocityChangeListener() {
  			public void onVelocityChange(RCServoVelocityChangeEvent e) {
  				// System.out.printf("Velocity Changed: %.3g\n", e.getVelocity());
  			}
          });
          
          servo.addPositionChangeListener(new RCServoPositionChangeListener() {
  			public void onPositionChange(RCServoPositionChangeEvent e) {
  				double sensorReading = e.getPosition();
  				System.out.printf("Servo Position Changed: %.3g\n", sensorReading);
  				int scaledSensorReading = (int) (1 * sensorReading);
  				// send value to server if changed since last reading
  				if (scaledSensorReading != lastSensorValue ) {
  					System.out.println("Sending new sensor value : " + scaledSensorReading);
  					sendToServer("Servo", + scaledSensorReading);
  					lastSensorValue = scaledSensorReading;
  				
  				
  				
  				}
  			}
          });
          
          servo.addTargetPositionReachedListener(new RCServoTargetPositionReachedListener() {
  			public void onTargetPositionReached(RCServoTargetPositionReachedEvent e) {
  				System.out.printf("Target Position Reached: %.3g\n", e.getPosition());
  			}
          });
      
          
      double x;
        x = slider.getVoltageRatio();
      System.out.println("Start slider Voltage Ratio is "+x);
    

  				
  		        
        
        
        
        

        
         // attach to the sensor and start reading
        try {      
                            
            System.out.println("\n\nGathering data for 15 seconds\n\n");
          
 // int val = Integer.parseInt(getFromServer()); - used by old servo explained in report. 
       
            
            
            pause(15);
            slider.close();
            rotation.close();

            System.out.println("\nClosed Voltage Ratio Input");
            
        } catch (PhidgetException ex) {
            System.out.println(ex.getDescription());
        }

    }

    public String sendToServer(String sensorName, int sensorValue){
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String fullURL = sensorServerURL + "?sensorname="+sensorName+"&sensorvalue="+sensorValue;
       System.out.println("Sending data to: "+fullURL);
        String line;
        String result = "";
        try {
           url = new URL(fullURL);
           conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           while ((line = rd.readLine()) != null) {
              result += line;
           }
           rd.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return result;
    	
    }
    
 /* ********************************Used to get motor data from server  - Explained in Report************************************************
  
  *   public String getFromServer(){
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String fullURL = sensorServerURL + "?sensorname=motor&getdata=true;";
       System.out.println("Sending data to: "+fullURL);
        String line;
        String result = "";
        try {
           url = new URL(fullURL);
           conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           while ((line = rd.readLine()) != null) {
              result += line;
           }
           rd.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return result;
    	
    }
    
   ********************************Used to get motor data from server****************************************** */
    
    
    
    
    
    
    
	private void pause(int secs){
        try {
			Thread.sleep(secs*150);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}




