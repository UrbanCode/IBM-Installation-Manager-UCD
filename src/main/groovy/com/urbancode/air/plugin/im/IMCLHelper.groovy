/**
 * � Copyright IBM Corporation 2014, 2016.  
 * This is licensed under the following license.
 * The Eclipse Public 1.0 License (http://www.eclipse.org/legal/epl-v10.html)
 * U.S. Government Users Restricted Rights:  Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

package com.urbancode.air.plugin.im;

import com.urbancode.air.CommandHelper;
public class IMCLHelper {


	def ch = new CommandHelper(new File("."));
	def imclExe;
	def dataLocation;
	IMCLHelper(Properties props) {
		imclExe = props['imclLocation']
		dataLocation = props['dataLocation'];
		if (!new File(imclExe).isFile()) {
			throw new IllegalArgumentException("${imclExe} is not a file, or not readable!");
		}
	}

	public void input(String responseFileLocation, String responseFileVariables, String secureStorageFile, String masterPasswordFile) {
		def args =['input', responseFileLocation];
		if( responseFileVariables?.trim() ){
			def responseFileVariablesArgs=[
				"-variables",
				responseFileVariables
			];
			args.addAll(responseFileVariablesArgs);
		}
		if( secureStorageFile?.trim() ){
			def secureStorageFileArgs=[
				"-secureStorageFile",
				secureStorageFile
			];
			args.addAll(secureStorageFileArgs);
		}
		if( masterPasswordFile?.trim() ){
			def masterPasswordFileArgs=[
				"-masterPasswordFile",
				masterPasswordFile
			];
			args.addAll(masterPasswordFileArgs);
		}


		execute("Executing response file", args);
	}

	public void uninstall(String packageName, String version) {
		def finPackName = packageName;
		if (version?.trim()) {
			finPackName +="_${version}";
		}
		execute("Uninstalling package", ['uninstall', finPackName]);
	}

	public void rollback(String packageName, String version) {
		def finPackName = packageName;
		if (version?.trim()) {
			finPackName +="_${version}";
		}
		execute("Rolling back package", ['rollback', finPackName]);
	}

	private void execute(String message, List cmdArgs) {
		def finalArgs = [imclExe, '-acceptLicense']
		if (dataLocation?.trim()) {
			def dataLocationArgs=[
				"-dataLocation",
				dataLocation
			];
			finalArgs.addAll(dataLocationArgs);
		}
		finalArgs.addAll(cmdArgs);
		ch.runCommand(message, finalArgs);
	}
}
