/*

Copyright notice
TOMM copyright 2016-2017, Zubeida C. Khan

Copying permission
This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metrics;

/**
 *
 * @author ZKhan
 */
public class toThread extends Thread {
     // create the threads and run them during one another
    
    public static final int THREADS =  Runtime.getRuntime().availableProcessors();
    
        public static void main(String[] args) {
            Thread [] manyThreads = new Thread[THREADS];
            for(int i = 0; i < THREADS;i++){
                manyThreads[i] = new Thread(new start());
                manyThreads[i].start();
            }
            
          /*  Thread t1 = new Thread(new start());
            Thread t2 = new Thread(new start());
            Thread t3 = new Thread(new start());
            Thread t4 = new Thread(new start());
            t1.start();
            t2.start();
            t3.start();
            t4.start();*/
        }
}
