/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopAndWait;

import java.util.Timer;

/**
 *
 * @author willi
 */
public class Mensaje {
    private String mensaje;
    private Timer timer;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
        this.timer = new Timer();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void addtask(Task task){
        this.timer.schedule(task, 50);
    }
    
    public void purgeTimer(){
        this.timer=new Timer();
    }
     
    public void cancelTimer(){
        this.timer.cancel();
    }
    
}
