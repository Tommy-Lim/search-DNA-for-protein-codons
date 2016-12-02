package findCodon;

import java.io.*;
import java.util.*;
import java.lang.*;

public class FindCodon {

//main method	
	public static void main(String[] args) throws IOException {
		
		String dna = createDNAstring();		
		finalProtein(dna);
		
		FindAllCodon(dna);
		
		//findProtein(dna);
		//AllGenesToString(dna);  // VERY GOOD EXAMPLE
		//ProteinSequence(dna);
		
		//System.out.println("CG ratio is: "+FindCGratio(dna));
		
		//printAllStarts(dna);
		
		/*int start = dna.indexOf("ATG");
		if(start==-1){
			System.out.println("Start codon not found");
		} else{
			System.out.println("Start codon found at "+start);
			System.out.println("Shortest stop codon found at "+findStopIndex(dna, start));
			System.out.println(dna.substring(start,findStopIndex(dna, start)+3));
		}*/
	}
	
	
	
//method to create the dna string  (assignment uses brca1line.fa
	public static String createDNAstring() throws IOException {
		
		File brca = new File("GRch38dnapart.fa");
		Scanner sc = new Scanner(brca);
		
		String dna = "";
		
		while (sc.hasNextLine()){
			dna = dna+ sc.nextLine();
		}
				
		dna=dna.toUpperCase();
		System.out.println("Length of dna is: "+dna.length());
		
		return dna;
		
		
	}	
	
	
//method to find start and stop codons and total protein	
	public static String findProtein(String dna){
		
		int start = dna.indexOf("ATG");
		if(start==-1){
			return "DNA start not found";
		}
		
		int stop=dna.indexOf("TAG", start+3);
		
		if((stop-start)%3==0){
			return dna.substring(start,stop+3);
		} else {
			//return "DNA stop not found";  //this is the soln. for Coursera but only looks at first stop instance
			for(int i=0;(stop-start)%3!=0; i++){
				stop = dna.indexOf("TAG",start+3+i);
				if(i>dna.length()){
					return "DNA Stop not found";
				}
			}
			return dna.substring(start, stop+3);
		}
	}
	
	
//method to find all start codons	
	public static void printAllStarts(String dna){
		int newstart=0;
		while (true){
			int loc = dna.indexOf("ATG",newstart);
			if (loc==-1){
				break;
			}
			System.out.println("ATG starts at "+loc);
			newstart=loc+3;
			
		}
	}
	
	
//method to find the closest stop codon/ shortest protein with ending tga, taa, or tag	
	public static int findStopIndex(String dna, int start){
		int stop1=dna.indexOf("TGA",start);
		if (stop1==-1||(stop1-start)%3!=0){
			stop1=dna.length();
		}
		int stop2=dna.indexOf("TAA",start);
		if (stop2==-1||(stop2-start)%3!=0){
			stop2=dna.length();
		}
		int stop3=dna.indexOf("TAG",start);
		if (stop3==-1||(stop3-start)%3!=0){
			stop3=dna.length();
		}
		return Math.min(stop1, Math.min(stop2, stop3));
	}
	
	
//method to find the cg ratio
	public static float FindCGratio(String dna){
		
		float countC=0;
		int step=0;
		
		while (dna.indexOf("C",step)!=-1){
			countC++;
			step=dna.indexOf("C",step)+1;
		}
		
		float countG=0;
		step=0;
		
		while (dna.indexOf("G",step)!=-1){
			countG++;
			step=dna.indexOf("G",step)+1;
		}
		
		//System.out.println("Count of C is: "+countC);
		//System.out.println("Count of G is: "+countG);
		
		float CG = (countC+countG)/dna.length();
		//System.out.println("The CG of "+dna+" is: "+CG);
			
		return CG;
	}

	
//method for finding all genes
	public static void AllGenesToString(String dna){
		
		//create arrays
		ArrayList<Integer> starts = new ArrayList<Integer>();
		ArrayList<String> genes = new ArrayList<String>();
		ArrayList<String> stops = new ArrayList<String>();
		
		//create stops
		stops.add("TGA");
		stops.add("TAA");
		stops.add("TAG");
				
		//create starts
		int startcodon=0;
		while(dna.indexOf("ATG",startcodon)!=-1){
			int startspot=dna.indexOf("ATG",startcodon);
			starts.add(startspot);
			//System.out.println(startspot);
			startcodon=startspot+1;
		}
		
		//for all starts	
		for(int startAll:starts){
			
			System.out.println("Start at: "+startAll);
		
			
			//and for all stops
			for(String stopcodon:stops){
				int stopAll=0;
				int loc= startAll;
				while(dna.indexOf(stopcodon,loc)!=-1){
					stopAll = dna.indexOf(stopcodon,loc);
					if((stopAll-startAll)%3==0){
						String gene = dna.substring(startAll,stopAll+3);
						genes.add(gene);
						System.out.println(gene);
						loc = stopAll+1;
					} else{
						loc = stopAll+1;
					}
				}
			}
					
			
			//This is where I wrote code for each of the stop codons individually
			/*
			int stopAll=0;
			int loc= startAll;
			while(dna.indexOf("TGA",loc)!=-1){
				stopAll = dna.indexOf("TGA",loc);
				if((stopAll-startAll)%3==0){
					String gene = dna.substring(startAll,stopAll+3);
					genes.add(gene);
					//System.out.println(gene);
					loc = stopAll+1;
				} else{
					loc = stopAll+1;
				}
			}
			loc=startAll;	
			stopAll=0;
			while(dna.indexOf("TAA",loc)!=-1){
				stopAll = dna.indexOf("TAA",loc);
				if((stopAll-startAll)%3==0){
					String gene = dna.substring(startAll,stopAll+3);
					genes.add(gene);
					//System.out.println(gene);
					loc = stopAll+1;
				} else{
					loc = stopAll+1;
				}
			}
			loc=startAll;	
			stopAll=0;
			while(dna.indexOf("TAG",loc)!=-1){
				stopAll = dna.indexOf("TAG",loc);
				if((stopAll-startAll)%3==0){
					String gene = dna.substring(startAll,stopAll+3);
					genes.add(gene);
					//System.out.println(gene);
					loc = stopAll+1;
				} else{
					loc = stopAll+1;
				}
			}
			*/
			
			
			
			System.out.println(genes.size());
		}
	}
	
	
//Method for finding proteins one after another - this method worked but not for the problem desc.
	
	public static void ProteinSequence(String dna){
		
		int currentstart=0;
		int stoploc=0;
		int confstop=0;
		int absstart=dna.indexOf("ATG",currentstart);
		ArrayList<String> stopcodons = new ArrayList<String>();
		stopcodons.add("TGA");
		stopcodons.add("TAA");
		stopcodons.add("TAG");
		
		ArrayList<String> sequencegenes = new ArrayList<String>();
		
		while(dna.indexOf("ATG",currentstart)!=-1){
			currentstart=dna.indexOf("ATG",currentstart);
			for(String stopcodon:stopcodons){
				//stoploc=dna.indexOf(stopcodon,currentstart);
				stoploc=currentstart;
				while(dna.indexOf(stopcodon,stoploc)!=-1){
					stoploc=dna.indexOf(stopcodon,stoploc);
					if((stoploc-currentstart)%3==0){
						if(confstop!=0){
							if((stoploc-currentstart)<(confstop-currentstart)){
								confstop=stoploc;
								break;
							} 	else{
								stoploc=stoploc+1;
							}
						} else{
							confstop=stoploc;
							break;
						}
					} else{
						stoploc=stoploc+1;
					}
				}
			}
			//System.out.println(currentstart);
			//System.out.println(confstop+3);
			if(confstop!=0){
				System.out.println(dna.substring(currentstart,confstop+3));
				sequencegenes.add(dna.substring(currentstart,confstop+3));
				currentstart=confstop+3;
				confstop=0;
				System.out.println(sequencegenes.size());
			} else{
			confstop=0;
			currentstart=currentstart+1;
			}
			
		}
		System.out.println(sequencegenes.size());
	}
	
	
//actually the method to add all proteins to a string	
	public static void finalProtein(String dna){
		
		int currentstart=0;
		int stoploc=0;
		int confstop=0;

		ArrayList<String> stopcodons = new ArrayList<String>();
		stopcodons.add("TGA");
		stopcodons.add("TAA");
		stopcodons.add("TAG");
		
		ArrayList<String> sequencegenes = new ArrayList<String>();
		
		
		//create the string of valid proteins
		while(dna.indexOf("ATG",currentstart)!=-1){
			currentstart=dna.indexOf("ATG",currentstart);
			for(String stopcodon:stopcodons){
				//stoploc=dna.indexOf(stopcodon,currentstart);
				stoploc=currentstart;
				while(dna.indexOf(stopcodon,stoploc+3)!=-1){
					stoploc=dna.indexOf(stopcodon,stoploc+3);
					if((stoploc-currentstart)%3==0){
						if(confstop!=0){
							if((stoploc-currentstart)<(confstop-currentstart)){
								confstop=stoploc;
								break;
							} 	else{
								break;
							}
						} else{
							confstop=stoploc;
							break;
						}
					} else{
						break;
					}
				}
			}
			//System.out.println(currentstart);
			//System.out.println(confstop+3);
			if(confstop!=0){
				//System.out.println(dna.substring(currentstart,confstop+3));
				sequencegenes.add(dna.substring(currentstart,confstop+3));
				currentstart=confstop+3;
				confstop=0;
				//System.out.println(sequencegenes.size());
			} else{
			confstop=0;
			currentstart=currentstart+1;
			}
			
		}
		System.out.println("Total number of genes is: "+sequencegenes.size());
		System.out.println("Longest genes is: "+longestGene(sequencegenes));
		
		
		//count all genes over 60 nucleotides long
		ArrayList<String> genesLong = new ArrayList<String>();
		for(String object:sequencegenes){
			if(object.length()>60){
				genesLong.add(object);
			}
			
		}
		System.out.println("The number of 60+ genes is: "+genesLong.size());
		
		//count all genes CG/whole ratio is 0.35+
		ArrayList<String> CGaccepts = new ArrayList<String>();
		for(String object2:sequencegenes){
			if(FindCGratio(object2)>0.35){
				CGaccepts.add(object2);
			}
		}
		System.out.println("The number of CG >0.35 is: "+CGaccepts.size());
		
	}

	
//find the number of a particular codon in a dna strand
	public static void FindAllCodon(String dna){
		
		int codoncount=0;
		int start = 0;
		String codon = "CTG";
		
		while(true){
			if(dna.indexOf(codon,start)==-1){
				break;
			} else{
				codoncount++;
				start = dna.indexOf(codon,start)+3;
			}
		}
		System.out.println("Count of "+codon+" is: "+codoncount);
	}
	
	
	public static int longestGene(ArrayList<String> genes){
		
		int longest=0;
		for(String gene:genes){
			if(gene.length()>longest){
				longest=gene.length();
			}
		}
		return longest;
	}
	
		
}
		
		
