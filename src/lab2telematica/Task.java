/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2telematica;

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
    
    public Task(UDPClient cl){
        this.cliente=cl;
    }
    @Override
    public void run() {
        System.out.println("time up resend ");
        try {
            this.cliente.enviar(this.cliente.getMensaje().getMensaje() );
        } catch (IOException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
