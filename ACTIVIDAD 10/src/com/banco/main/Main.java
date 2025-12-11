package com.banco.main;

import com.banco.ui.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      new LoginFrame().setVisible(true);
    });
  }
}
