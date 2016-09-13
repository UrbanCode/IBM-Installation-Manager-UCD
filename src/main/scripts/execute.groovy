/**
 * � Copyright IBM Corporation 2014, 2016.  
 * This is licensed under the following license.
 * The Eclipse Public 1.0 License (http://www.eclipse.org/legal/epl-v10.html)
 * U.S. Government Users Restricted Rights:  Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import com.urbancode.air.AirPluginTool;
import com.urbancode.air.plugin.im.IMCLHelper

def apTool = new AirPluginTool(this.args[0], this.args[1])

def props = apTool.getStepProperties();

def workDir = new File(".");
def helper = new IMCLHelper(props);
def responseFile = props['responseFile'];
def responseFileVariables = props['responseFileVariables'];
def secureStorageFile = props['secureStorageFile'];
def masterPasswordFile = props['masterPasswordFile'];
if (!new File(responseFile).isFile()) {
    System.out.println("Response file not found, not readable, or is a directory!");
    System.exit(1);
}

helper.input(responseFile,responseFileVariables,secureStorageFile,masterPasswordFile)
