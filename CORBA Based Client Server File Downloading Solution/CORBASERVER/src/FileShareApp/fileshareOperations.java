package FileShareApp;


/**
* FileShareApp/fileshareOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from fileshare.idl
* Tuesday, May 24, 2022 9:18:48 PM PKT
*/

public interface fileshareOperations 
{
  String RegisterFile (String argclientId, String argipAddress, int argportNumber, String argclientName, String argfileName, String argfileLocation, String argfileImage);
  String deleteFile (String argclientId, String argfileName);
  String updateFile (int FileID, String argclientId, String argipAddress, int argportNumber, String argclientName, String argfileName, String argfileLocation, String argfileImage);
  String listFile (int loopId, org.omg.CORBA.IntHolder FileID, org.omg.CORBA.StringHolder argclientId, org.omg.CORBA.StringHolder argipAddress, org.omg.CORBA.IntHolder argportNumber, org.omg.CORBA.StringHolder argclientName, org.omg.CORBA.StringHolder argfileName, org.omg.CORBA.StringHolder argfileLocation, org.omg.CORBA.StringHolder argfileImage);
  String printFiles (int loopId, org.omg.CORBA.StringHolder argclientId, org.omg.CORBA.StringHolder argipAddress, org.omg.CORBA.IntHolder argportNumber, org.omg.CORBA.StringHolder argclientName, org.omg.CORBA.IntHolder FileID, org.omg.CORBA.StringHolder argfileName, org.omg.CORBA.StringHolder argfileLocation, org.omg.CORBA.StringHolder argfileImage);
  int getTotalNumberOfFiles ();
  int getLastFileID ();
  int refreshList ();
  void shutdown ();
} // interface fileshareOperations
