package sic.input;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quickLinks.QuickLinks;

public class ButtonS extends Button {

	public ButtonS(String a, final String targetMethodName) {
		super(a);
		this.setFont(new Font("Verdana", Font.BOLD, 10));
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuickLinks.invoke(targetMethodName);
			}
		});
	}

	public ButtonS(String a, final String targetMethodName,
			final String... args) {
		super(a);
		this.setFont(new Font("Verdana", Font.BOLD, 10));
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuickLinks.invoke(targetMethodName, args);
			}
		});
	}

}
