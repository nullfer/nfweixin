/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.sales.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author citysky
 */
public class SequenceUtil {

	private static long seqIndex = 0;

	public synchronized static String getSequence() {
		if (seqIndex > 999) {
			seqIndex = 0;
		}
		return new Date().getTime() + "" + new DecimalFormat("000").format(seqIndex++);
	}

	public static long getSequenceLong() {
		return Long.parseLong(getSequence());
	}

	public static void main(String args[]) {
		Runnable run = new Runnable() {
			@Override
			public void run() {
				for (int j = 0; j < 10; j++) {
					try {
						Thread.sleep(5);
						System.out.println(Long.parseLong(getSequence()));
					} catch (InterruptedException ex) {
						Logger.getLogger(SequenceUtil.class.getName()).log(Level.SEVERE, null, ex);
					}

				}
			}
		};
		Thread thread1 = new Thread(run);
		Thread thread2 = new Thread(run);
		Thread thread3 = new Thread(run);
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
