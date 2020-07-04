package com.example.knjizara;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uros
 */
public class Klijent {
    public static String nesto;
    Socket s;
    PrintWriter pw;
    BufferedReader br;
    InputStreamReader isr;
    DataOutputStream dos;
    JSONArray lista;
    boolean provera = false;

    public boolean testServer() {

        try {

            Thread thread= new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        s = poveziSaServerom();
                        zatvoriSocket();
                        provera = true;
                    }
                    catch (Exception e) {
                        System.out.println(e);
                        provera = false;
                    }
                }
            });
            thread.start();
            thread.join();

            if(provera == false)
                return false;
            else
                return true;

        } catch (Exception ex) {
            System.out.println("Greska0:: "+ex);
            return false;
        }
    }

    public void sendM0(String poruka) {
        try {
        Thread threadForSendingMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    s = poveziSaServerom();
                    pw = new PrintWriter(s.getOutputStream());
                    pw.write(poruka+"\n");
                    pw.flush();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        threadForSendingMessage.start();
        threadForSendingMessage.join();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
    public void setLista () {
        try {
            Thread threadForReceivingMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        isr = new InputStreamReader(s.getInputStream());
                        br = new BufferedReader(isr);
                        String message = br.readLine();


                        JSONObject jObject = new JSONObject(message);
                        JSONArray jArray = jObject.getJSONArray("lista");
                        printJSONArray(jArray);

                        lista = jArray.getJSONArray(0);


                        zatvoriSocket();
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }

                }
            });
            threadForReceivingMessage.start();
            threadForReceivingMessage.join();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public void printJSONArray(JSONArray jArray) {


//        try {
//            System.out.println(jArray.getJSONArray(0));
//            for(int j=0;j<jArray.getJSONArray(0).length();j++) {
////                    System.out.println(jArray.getJSONArray(0).get(j));
//            }
//        } catch (JSONException ex) {
//            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public JSONArray sendM(String poruka) {
        try {
            
            if(poruka.equals("test")) {
                
                sendM0(poruka);
                zatvoriSocket();
                return null;
            }
            else if(poruka.equals("najpKnjige")) {
                sendM0(poruka);

                setLista();

                zatvoriSocket();

                return lista;
            }
            
            else if(poruka.equals("besplKnjige")) {
                
                sendM0(poruka);

                setLista();
                
                zatvoriSocket();

                return lista;
            }
            
            else if(poruka.startsWith("mojeKnjige")) {
                
                sendM0(poruka);

                setLista();

                zatvoriSocket();

                return lista;
            }
            
            
            else if(poruka.equals("hronLista")) {
                
                sendM0(poruka);

                setLista();

                zatvoriSocket();

                return lista;
            }

            else if(poruka.startsWith("ids")) {


                sendM0(poruka);

                setLista();

                zatvoriSocket();

                return lista;
            }

            
            else if(poruka.startsWith("id")) {
                
                sendM0(poruka);

                setLista();
                
                zatvoriSocket();
                return lista;
            }
            
            else if(poruka.startsWith("komentari")) {
                
                sendM0(poruka);

                setLista();
                
                zatvoriSocket();
                return lista;
            }
            else if(poruka.startsWith("kategorija")) {

                sendM0(poruka);

                setLista();

                zatvoriSocket();
                return lista;
            }

            else if(poruka.startsWith("countOsoba")) {

                sendM0(poruka);

                setLista();

                zatvoriSocket();
                return lista;
            }

            else if(poruka.startsWith("addUser")) {

                sendM0(poruka);

                setLista();

                zatvoriSocket();
                return lista;
            }
            else if(poruka.startsWith("getUser")) {

                sendM0(poruka);

                setLista();

                zatvoriSocket();

                return lista;
            }

            else if(poruka.equals("countNarudzbine")) {

                sendM0(poruka);

                setLista();

                zatvoriSocket();
                return lista;
            }
            else if(poruka.startsWith("koMentarisi")) {
                sendM0(poruka);

                setLista();

                zatvoriSocket();

                return lista;
            }
            else if(poruka.startsWith("reg")) {
                sendM0(poruka);

                setLista();

                zatvoriSocket();

                return lista;
            }
            
            
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public Socket poveziSaServerom() {
        try {
            s = new Socket("192.168.1.4",34300);
            return s;
        } catch (IOException ex) {
            return null;
        }
    }
    
    public void zatvoriSocket() {
        try {
            s.close();
        } catch (IOException ex) {
            System.out.println("greska: : " + ex);
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void zatvoriSve() {
        try {
            isr.close();
            br.close();
            pw.close();
            s.close();
        } catch (Exception ex) {
            System.out.println("greska.");
        }
    }

    public Socket getS() {
        return s;
    }
    
}
