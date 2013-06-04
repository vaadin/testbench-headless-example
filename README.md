Headless TestBench
==================

This is an example project accompanying the blog post at http://vaadin.com/blog.
It is based on the Addressbook Tutorial found at http://vaadin.com/tutorial


Running the example test
------------------------
    mvn verify


Importing in Eclipse
--------------------
Make sure you have "Eclipse IDE for Java EE Developers" and Maven integration "m2e-wtp" installed. You will get Eclipse from http://eclipse.org/downloads/ and plugins through Help -> Eclipse Marketplace... menu

To checkout and run the project from Eclipse, do:
- File -> Import...
- Check out Maven Projects from CMS
- Choose Git from SCM menu and set URL to git://github.com/jojule/SimpleAddressbook.git
- Now you should have checked out the project
- To run it, choose Run As -> Run on Server
- Start experimenting

Note that if you are missing EGit plugin, "Maven SCM Handler for EGit" or a local server to run the address book on, you will be asked to install these while doing the above.
