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

def workDir = new File(".");
def ch = new CommandHelper(new File("."));
def mode = props['mode'];
def installationDirectory = props['installationDirectory'];
def dataLocation = props['dataLocation'];
def log = props['log'];
def installationKitDirectory = props['installationKitDirectory']
def isWindows = (System.getProperty('os.name') =~ /(?i)windows/).find()

def commandName=installationKitDirectory+System.getProperty('file.separator');
if(mode.equals("administrator")){
	if (isWindows){
		commandName+="installc.exe";
	}
	else{
		commandName+="installc";
	}
}else{
	if (isWindows){
		commandName+="userinstc.exe";
	}
	else{
		commandName+="userinstc";
	}
}



def args =[commandName, "-acceptLicense"];
if( log?.trim() ){
	def logArgs=["-log", log];
	args.addAll(logArgs);
}
if( installationDirectory?.trim() ){
	def installationDirectoryArgs=[
		"-installationDirectory",
		installationDirectory
	];
	args.addAll(installationDirectoryArgs);
}
if( dataLocation?.trim() ){
	def dataLocationArgs=["-dataLocation", dataLocation];
	args.addAll(dataLocationArgs);
}
ch.runCommand("Installing Installation Manager:",args);
