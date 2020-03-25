package cn.yang.listener.api;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import org.joda.time.DateTime;

@WebListener
public class ListenerHello implements ServletRequestListener {

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    System.out.println("--- hello request destroyed ---" + DateTime.now());
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    System.out.println("--- hello request init ---" + DateTime.now());
  }
}
