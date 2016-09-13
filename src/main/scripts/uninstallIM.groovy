/**
 * � Copyright IBM Corporation 2014, 2016.
 * This is licensed under the following license.
 * The Eclipse Public 1.0 License (http://www.eclipse.org/legal/epl-v10.html)
 * U.S. Government Users Restricted Rights:  Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import com.urbancode.air.AirPluginTool;
import com.urbancode.air.CommandHelper;

def apTool = new AirPluginTool(this.args[0], this.args[1])

def props = apTool.getStepProperties();
def isWindows = (System.getProperty('os.name') =~ /(?i)windows/).find()

def workDir = new File(".");
def ch = new CommandHelper(new File("."));
def mode = props['mode'];
def dataLocation = props['dataLocation'];


def commandName;
if(mode.equals("administrator")){
 if(isWindows){
 commandName="uninstallc.exe";
 }
 else{
 commandName="uninstallc";
 }
}else{
 if(isWindows){
 commandName="userinstc.exe";
 }else{
 commandName="uninstallc";
 }
}

def args =[dataLocation+System.getProperty('file.separator')+"uninstall"+System.getProperty('file.separator')+commandName];

ch.runCommand("Uninstalling Installation Manager:",args);
