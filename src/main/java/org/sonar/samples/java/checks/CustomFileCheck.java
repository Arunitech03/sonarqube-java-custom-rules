package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;


import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.model.expression.InstanceOfTreeImpl;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.NewClassTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.util.List;
 
@Rule(
		  key = "YT_File_Creation_Check",
		  name = "File related properties must be validated",
		  description = "Whenever a file processing takes place make sure that necessary OWASP ESAPI validations are put in place.",
		  priority = Priority.CRITICAL,
		  tags = {"bug"})

public class CustomFileCheck extends IssuableSubscriptionVisitor {
 

	  public List<Tree.Kind> nodesToVisit() {
		    // Register to the kind of nodes you want to be called upon visit.
		    return ImmutableList.of(
		    		Tree.Kind.INSTANCE_OF,
		    		Tree.Kind.NEW_CLASS,
		    		Tree.Kind.METHOD_INVOCATION);
		    		
		  }

		  @Override
		  public void visitNode(Tree tree) {
			  	  if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
				  MethodInvocationTree mit = (MethodInvocationTree) tree;
				  if("createNewFile".equals(mit.symbol().name()) || "fileUpload".equals(mit.symbol().name()) ||  "uploadFileAttachment".equals(mit.symbol().name())) {
					  reportIssue(mit, "File is getting processed - Enforce all the File Validation");
					  System.out.println("Method Name Matches loop");
				  }
			 }
			 if (tree.is(Tree.Kind.NEW_CLASS)) {
				  NewClassTree newClassTree = (NewClassTree) tree;
				  String newClassTypeName = newClassTree.identifier().symbolType().name();
				  if("File".equals(newClassTypeName)) {
					  reportIssue(newClassTree, "File is getting processed - Enforce all the File Validation");
					  System.out.println("New File  " + newClassTypeName);
				  }
			  }
		  }
}
