import java.awt.event
import java.awt.event.{ActionListener, MouseAdapter, MouseListener}

import javafx.event.ActionEvent
import javax.swing.JButton

object ExampleImplicit extends App{
  val button = new JButton()
  implicit  def functoAdapter(f:ActionEvent=>Unit) = new MouseAdapter {
   def actionPerformed(event: ActionEvent) = f(event)
 }
  button.addMouseListener((_:ActionEvent)=>println("click"))

  implicit def intToString(x: Int) = x.toString

  def sum(x:Int,y:Int): String ={
    x+y
    }
  println(sum(4,6))
}
