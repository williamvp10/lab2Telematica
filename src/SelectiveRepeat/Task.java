/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SelectiveRepeat;

import StopAndWait.*;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willi
 */
public class Task extends TimerTask {
    private UDPClient cliente;
    private Mensaje m;
    
    public Task(UDPClient cl,Mensaje m){
        this.cliente=cl;
        this.m=m;
    }
    
    @Override
    public void run() {
        System.out.println("time up resend ");
        try {
            this.cliente.enviar(this.m);
        } catch (IOException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
