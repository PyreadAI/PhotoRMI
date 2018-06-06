# PhotoRMI

***In progress: RMI server/client

Setup Instruction:

1st ----- Server Folder Compile -----

1. under /Server Directory run javac *.java in Windows cmd or powershell
2. rmic RIImplement, which creates RIImplement_Stub.class

2nd ----- Client Folder Compile -----

1. copy RIImplement_Stub.class from Server folder and paste in 		/Client folder
2.  under /Client Directory run javac *.java in Windows cmd or powershell

3rd ----- Running ------

1. under /Server folder run "start rmiregistry 10001" in cmd, leave the popup window open
2. run "start java ExampleServer", should see server ready
3. then under /Client folder run "java ExampleClient", should see success in both client/server