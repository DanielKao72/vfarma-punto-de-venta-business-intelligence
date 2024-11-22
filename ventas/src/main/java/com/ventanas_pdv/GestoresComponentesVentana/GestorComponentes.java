package com.ventanas_pdv.GestoresComponentesVentana;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.ventanas_pdv.ComponentesVentana.InformacionBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionEstilosBoton;
import com.ventanas_pdv.ComponentesVentana.InformacionPanel;

public class GestorComponentes {

    public static JButton crearBoton(InformacionBoton informacionBoton, InformacionEstilosBoton estilosBoton) {
        JButton boton = new JButton(informacionBoton.obtenerTextoBoton());
        boton.setBorder(informacionBoton.obtenerBordes());
        boton.setBackground(estilosBoton.obtenerColorFondo());
        boton.setForeground(estilosBoton.obtenerColorTexto());
        boton.setFont(estilosBoton.obtenerFuenteTexto());
        boton.setIcon(estilosBoton.obtenerIcono());
        boton.setVerticalTextPosition(estilosBoton.obtenerPosicionVerticalTexto());
        boton.setHorizontalTextPosition(estilosBoton.obtenerPosicionHorizontalTexto());
        boton.setFocusPainted(false);

        return boton;
    }

    public static JPanel crearPanel(InformacionPanel informacionPanel, int anchoPantalla, int alturaPantalla) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(anchoPantalla, (int) (alturaPantalla * informacionPanel.obtenerAlturaRelativa())));
        panel.setBackground(informacionPanel.obtenerColorFondo());
        panel.setLayout(informacionPanel.obtenerTipoDisposicion());
        
        return panel;
    }
    
}