package test_for_every_dont_know;

import java.io.File;

public class linkList {
	protected Node[] nodelst;
	protected Node treeroot;
	protected int count ;
	public linkList() {
		nodelst = new Node[2*Node.getNodeLength()];
		treeroot=null;
		count=0;
	}
	public int findindex(String strlst[],String str)//寻找字符串数组strlst中str的索引,未找到则返回-1；//指针连接时使用
	{
		int i,num = -1;
		for(i=0;i<strlst.length;i++)
		{
			if(strlst[i].equalsIgnoreCase(str))
			{
				num=i;
				break;
			}
		}
		return num;
		
	}
	public int Strlength(String strlst[])//字符串数组(已填充长度+1)//新建指针时使用
	{
		int i;
		for(i=0;i<strlst.length;i++)
		{
			if(strlst[i].equalsIgnoreCase(""))
			{
				break;
			}
		}
		return i;
	}
	public int Strlength(Node[] nodelst)//函数重载，返回Node数组(已填充长度+1)
	{
		int top=0;
		for(int i=0;i<nodelst.length;i++)
		{
			if(nodelst[i]==null)
				{
				top=i;
				break;
				}
		}
		return top;
	}
	public Node searchnode(String str)//在nodelst中寻找字符串str的指针，未找到则返回null//连接指针
	{
		Node findnode=null;
		boolean findflag=false;
		for(int i=0;i<count;i++)
		{	
			if(nodelst[i].getData().equalsIgnoreCase(str))
			{
				findnode= nodelst[i];
				findflag=true;
			}
		}
		if(findflag==true)
		{
			return findnode;
		}
		else{
			return null;
		}
	}
	public void insertnode(String str1,String str2)//插入字符串//功能1
	{
		Node tmp1;
		Node tmp2;
		if(count==0)//刚开始时，空树，创建树
		{
			tmp1=new Node(str1);
			tmp2=new Node(str2);
			tmp2.getWeightparent()[Strlength(tmp2.getWeightparent())]=str1;
			tmp2.getWeightnum()[findindex(tmp2.getWeightparent(),str1)]++;
			tmp1.getNext()[tmp1.getNumber()]=tmp2;
			tmp1.setNumber(tmp1.getNumber() + 1);
			nodelst[0]=tmp1;
			nodelst[1]=(tmp2);
			count=2;
			treeroot=tmp1;
			//root=tmp1;
			//return root;
		}else//树非空
		{
				tmp1=searchnode(str1);//寻找str1指针
				tmp2=searchnode(str2);//寻找str2指针
				if(tmp2==null)//未找到str2，则新建str2指针并连接
				{
					tmp2=new Node(str2);
					tmp2.getWeightparent()[Strlength(tmp2.getWeightparent())]=str1;//创建父节点信息
					tmp2.getWeightnum()[findindex(tmp2.getWeightparent(),str1)]++;//父节点权重+1
					tmp1.getNext()[tmp1.getNumber()]=tmp2;//连接子结点
					tmp1.setNumber(tmp1.getNumber() + 1);//子结点计数+1
					nodelst[count]=tmp2;//将新建的str2指针加入数组
					count++;//节点计数+1
				}
				else{//找到str2，将str2指针连接进去
					if(findindex(tmp2.getWeightparent(),str1)==-1)//若str1不是str2父节点，创建连接
					{
						tmp2.getWeightparent()[Strlength(tmp2.getWeightparent())]=str1;//创建父节点信息
						tmp2.getWeightnum()[findindex(tmp2.getWeightparent(),str1)]++;//父节点权重+1
						tmp1.getNext()[tmp1.getNumber()]=tmp2;//连接子结点
						tmp1.setNumber(tmp1.getNumber() + 1);//子结点计数+1
					}
					else{//str1是str2父节点，权重+1
						tmp2.getWeightnum()[findindex(tmp2.getWeightparent(),str1)]++;//权重+1
					}
					//tmp2.weightparent[Strlength(tmp2.weightparent)]=str1;
				}
		}
		
		//return root;
		
	}
	public int indexOfNodeInlst(String str)//在Node数组中返回str的索引
	{
		int tmp=-1;
		for(int i=0;i<count;i++)
		{
			if(nodelst[i].getData().equalsIgnoreCase(str))
			{
				tmp=i;
			}
		}
		return tmp;
	}
	public Node[] searchroad(String str1,String str2,Node visitlst[])//查找路是否存在，如果是，返回路径节点数组（有序），没有的话，返回数组为str1节点或者空数组
	{
		if(indexOfNodeInlst(str1)==-1)//节点不存在，返回空数组
		{
			//System.out.println("the string "+str1+"not exiet");
			return visitlst;
		}
		if(indexOfNodeInlst(str2)==-1)//节点不存在，返回空数组
		{
			//System.out.println("the string "+str2+"not exiet");
			return visitlst;
		}
		if(indexOfNodeInlst(str1)>indexOfNodeInlst(str2))//seek->to路径不存在，返回空数组
		{
			//System.out.println("No bridge words from "+str1+" to "+str2+"!");
			return visitlst;
		}
		else
		{
			Node p,q;
			//Node visitlst[]=new Node[Node.nodeLength];
			int top=0;
			visitlst[0]=nodelst[indexOfNodeInlst(str1)];
			p=visitlst[0];
			p.setVisitflag(true);
			do{										//寻找路径
				if(p.getVisitcount()<p.getNumber())
				{
					if(p.getNext()[p.getVisitcount()].isVisitflag()==false)
					{
						p.setVisitcount(p.getVisitcount() + 1);
						p=p.getNext()[p.getVisitcount()-1];
						top++;
						visitlst[top]=p;
						p.setVisitflag(true);
					}
					else{
						p.setVisitcount(p.getVisitcount() + 1);
					}
					
					
				}
				else{
					if(top!=0)
					{
						top--;
						p=visitlst[top];
					}
					else
					{
						return visitlst;
					}
				}
			}while(!str2.equalsIgnoreCase(p.getData()));
			for(int i=top+1;i<visitlst.length;i++)//清空非路径部分
			{
				visitlst[i]=null;
			}
			for(int i=0;i<Strlength(nodelst);i++)//恢复访问标志为false，为下次访问做准备
			{
				nodelst[i].setVisitflag(false);;
				nodelst[i].setVisitcount(0);
			}
		return visitlst;
		}
	}//searchroad
	public void displaytree()//功能2 展示图
	{
		Node tmp;
		String type = "jpg";
		GraphViz gv = new GraphViz();
	    gv.addln(gv.startgraph());
	    for(int i=0;i<count;i++)
	    {
	    	for(int j=0;j<nodelst[i].getNumber();j++)
	    	{
	    		//if(nodelst[i].!=0)
	    		//{
	    			gv.addlnlabel(nodelst[i].getData()+"->"+nodelst[i].getNext()[j].getData(),""+nodelst[i].getNext()[j].getWeightnum()[findindex(nodelst[i].getNext()[j].getWeightparent(),nodelst[i].getData())]);
	    		//}
	    	}
	    }
	    gv.addln(gv.endgraph());
	    File out = new File("D:\\java project\\test_for_every_dont_know\\out." + type);    // Windows
	    //System.out.println(gv.getDotSource());
	    byte[] temp=gv.getGraph( gv.getDotSource(), type );
	    //System.out.println(temp);
	    gv.writeGraphToFile( temp, out );
	}
	public void searchBridgeWords(String str1,String str2)//功能3查询桥接词
	{
		int visitnodenum=2*Node.getNodeLength(),top = 0,findin1,findin2;
		Node visitlst[]=new Node[visitnodenum];
		for(int i=0;i<visitnodenum;i++)
		{
			visitlst[i]=null;
		}
		findin1=indexOfNodeInlst(str1);
		findin2=indexOfNodeInlst(str2);
		if(findin1==-1&&findin2==-1)
		{
			System.out.println("No “"+str1+"” and “"+str2+"” in the graph"); 
			return;
		}
		else if(findin1==-1)
		{
			System.out.println("No “"+str1+"” in the graph");
			return;
		}
		else if(findin2==-1)
		{
			System.out.println("No “"+str2+"” in the graph");
			return;
		}
		visitlst=searchroad(str1,str2,visitlst);//调用路径搜索函数searchroad获得路径数组
		for(int i=0;i<visitnodenum;i++)
		{
			if(visitlst[i]==null){
				top=i;
				break;
			}
		}
		//System.out.println("================Task 3================");
		if(top<=2)
		{
			System.out.println("No bridge words from "+str1+" to "+str2+"!");
		}
		else
		{
			System.out.println("The bridge words from "+ str1+ " to "+ str2+" are: ");
			for(int i=1;i<top-1;i++)
			{
				System.out.print(visitlst[i].getData());
				if(i!=top-2)
				{
					System.out.print(",");
				}
				else{
					System.out.println(".");
				}
			}
		}
		return;
	}//searchBridgeWords

	public void createNewTxtBasisOfBridgeWords(String strinput)//功能4根据bridge word生成新文本
	{
		String regex="[^\\p{Alpha}]+";
		String[] strspilt=strinput.split(regex);
		String[] strout=new String[(int)(10*strspilt.length)];
		for(int i=0;i<strout.length;i++){
			strout[i]="";
		}
		String[] strtmp=new String[Node.getNodeLength()];
		for(int i=0;i<strtmp.length;i++){
			strtmp[i]="";
		}
		Node[] strnodetmp=new Node[Node.getNodeLength()];
		for(int i=0;i<strnodetmp.length;i++)
		{
			strnodetmp[i]=null;
		}
		strout[0]=strspilt[0];
		int nodetmplength;
		for(int i=0;i<strspilt.length-1;i++)
		{
			strnodetmp=searchroad(strspilt[i],strspilt[i+1],strnodetmp);//调用路径搜索函数searchroad获得路径数组
			nodetmplength=Strlength(strnodetmp);//路径数组长度
			if(nodetmplength<=2)//长度<=2，无连接词
			{
				strout[Strlength(strout)]=strspilt[i+1];//添加词语
				continue;
			}
			else//有连接词
			{
				for(int j=0;strnodetmp[j]!=null;j++)
				{
					strtmp[j]=strnodetmp[j].getData();
				}
				for(int k=1;!"".equals(strtmp[k]);k++)//添加词语
				{
					strout[Strlength(strout)]=strtmp[k];
				}
				for(int j=0;j<Strlength(strnodetmp);j++)
				{
					strnodetmp[j]=null;
				}
			}
		}
		//System.out.println("================Task 4================");
		for(int i=0;i<Strlength(strspilt);i++)
		{
			System.out.print(strspilt[i]);
			if(i!=Strlength(strspilt)-1)
			{
				System.out.print(" ");
			}
			else{
				System.out.println(".");
			}
		}
		for(int i=0;i<Strlength(strout);i++)
		{
			System.out.print(strout[i]);
			if(i!=Strlength(strout)-1)
			{
				System.out.print(" ");
			}
			else{
				System.out.println(".");
			}
		}
		for(int i=0;i<strout.length;i++){
			strout[i]="";
		}
	}//createNewTxtBasisOfBridgeWords
	public boolean isallvisited()//判断是否全部被访问过
	{
		boolean flag=true;
		for(int i=0;i<Strlength(nodelst);i++)
		{
			if(nodelst[i].isVisitflag()==false)
			{
				flag=false;
				break;
			}
		}
		return flag;
		
	}
	public void shortestroad(String str1,String str2)//功能需求5：计算两个单词之间的最短路径
	{
		Node resultlst[][] = new Node[Node.getNodeLength()][];
		for(int i=0;i<Node.getNodeLength();i++)
		{
			resultlst[i]=new Node[2*Node.getNodeLength()];
		}
		Node tmpnode[] = new Node[2*Node.getNodeLength()],minroad[][]=new Node[Node.getNodeLength()][];
		for(int i=0;i<Node.getNodeLength();i++)
		{
			minroad[i]=new Node[2*Node.getNodeLength()];
		}
		int count=0;
		int maxofallroads;
		boolean allnodevisitflag;//全体访问标志，当标志为true表示全体节点被访问
		tmpnode=searchroad(str1,str2,tmpnode);
		if(Strlength(tmpnode)>2)
		{
			resultlst[0]=tmpnode;
			do{
				for(int i=0;i<=count;i++)//对已经获得的路径添加访问过的标志
				{
					for(int j=1;j<Strlength(resultlst[i])-1;j++)
					{
						resultlst[i][j].setSelfvisitcount(resultlst[i][j].getSelfvisitcount()+1);
						if(resultlst[i][j].getNumber()==resultlst[i][j].getSelfvisitcount())
						{
							resultlst[i][j].setVisitflag(true);
						}
						else{
							break;
						}
					}//break;
				}
				allnodevisitflag=isallvisited();//获得全体访问标志
				tmpnode=new Node[2*Node.getNodeLength()];
				if(allnodevisitflag==false)//并非全体都被访问，则进行路径查找
				{
					tmpnode=searchroad(str1,str2,tmpnode);//调用路径搜索函数searchroad获得路径数组
				}
				if(Strlength(tmpnode)>2)//即路径存在，向路径数组中添加路径
				{
					count++;
					resultlst[count]=tmpnode;
					tmpnode=new Node[2*Node.getNodeLength()];
				}
				else{
					allnodevisitflag=true;//路径不存在，将全体访问标志改为true，表示访问结束，此时结束循环
				}
				for(int i=0;i<Strlength(nodelst);i++)
				{
					nodelst[i].setVisitcount(0);
					nodelst[i].setVisitflag(false);
					nodelst[i].setSelfvisitcount(0);
				}
			}while(allnodevisitflag==false);
			maxofallroads = count+1;//冒泡排序
			if(maxofallroads>1)
			{
				for(int i=0;i<maxofallroads;i++)
				{
					for(int j=0;j<=maxofallroads-j-1;j++)
					{
						if(Strlength(resultlst[j])>=Strlength(resultlst[j+1])){
							tmpnode=resultlst[j];
							resultlst[j]=resultlst[j+1];
							resultlst[j+1]=tmpnode;
						}
					}
				}
			}
			
			int minroadlength=Strlength(resultlst[0]);
			if(maxofallroads==1)
			{
				System.out.println("********The road between “"+str1+"” and “"+str2+"” is:    ");
			}
			else{
				System.out.println("********The "+maxofallroads+" roads between “"+str1+"” and “"+str2+"” are:    ");
			}
			for(int i=0;i<maxofallroads;i++)
			{
				if(Strlength(resultlst[i])==minroadlength)
				{
					for(int j=0;j<Strlength(resultlst[i]);j++)
					{
						System.out.print(resultlst[i][j].getData());
						if(j!=Strlength(resultlst[i])-1)
						{
							System.out.print("->");
						}
						else{
							System.out.print(".");
							//System.out.println();
						}
					}
					
				}
				else{
					break;
				}
				System.out.println();
			}
			
		}
		else{
			System.out.println("********There is no road between “"+str1+"” and “"+str2+"”");
		}
		for(int i=0;i<Strlength(nodelst);i++)
		{
			
			nodelst[i].setVisitcount(0);
			nodelst[i].setVisitflag(false);
			nodelst[i].setSelfvisitcount(0);
		}
		allnodevisitflag=false;
		count=0;
		for(int i=0;i<Node.getNodeLength();i++)
		{
			resultlst[i]=new Node[2*Node.getNodeLength()];
		}
		return;
		//System.out.println("================Task 5================");
		
	}//shortestroad
	public void randomvisit()
	{
		int randhead,randinttmp;
		randhead=(int)(Math.random()*count);
		Node randomroot=nodelst[randhead];
		String randomVisitRoad[]=new String[count*2];
		for(int i=0;i<randomVisitRoad.length;i++){
			randomVisitRoad[i]="";
		}
		Node p,q;
		boolean allvisitofnodelst=false;
		Node visitlst[]=new Node[2*Node.getNodeLength()];
		int top=0;
		visitlst[0]=randomroot;
		p=visitlst[0];
		randomVisitRoad[Strlength(randomVisitRoad)]=p.getData();
		p.setSelfvisitcount(p.getSelfvisitcount() + 1);
		if(p.getSelfvisitcount()==p.getNumber())
		{
			p.setVisitflag(true);
		}
		do{
			if(!allvisitofnodelst)
			{
				randinttmp=(int)(Math.random()*p.getNumber());
				if(p.getNumber()==0)
				{
					allvisitofnodelst=true;
				}
				else if(p.getNext()[randinttmp].isVisitflag()==false)
				{
					p.setVisitcount(p.getVisitcount() + 1);
					p=p.getNext()[randinttmp];
					randomVisitRoad[Strlength(randomVisitRoad)]=p.getData();
					p.setSelfvisitcount(p.getSelfvisitcount() + 1);
					if(p.getSelfvisitcount()==p.getNumber())
					{
						p.setVisitflag(true);
					}
					top++;
					visitlst[top]=p;
				}
				else{
					p=p.getNext()[randinttmp];
					randomVisitRoad[Strlength(randomVisitRoad)]=p.getData();
					allvisitofnodelst=true;
				}
			}

		}while(!allvisitofnodelst);
		//System.out.println("================Task 6================");
		for(int i=0;i<Strlength(randomVisitRoad);i++)
		{
			System.out.print(randomVisitRoad[i]);
			if(i!=Strlength(randomVisitRoad)-1)
			{
				System.out.print("->");
			}
			else{
				System.out.println(".");
			}
		}
		for(int i=0;i<Strlength(nodelst);i++)
		{
			nodelst[i].setVisitcount(0);
			nodelst[i].setVisitflag(false);
			nodelst[i].setSelfvisitcount(0);
		}
		
	}//randomWalk
}//linkList
