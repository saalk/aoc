package nl.saalks.springbootvuejs.service.aoc22;

import nl.saalks.springbootvuejs.service.AdventOfCode;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Day5 implements AdventOfCode {
	
	/**
	 Day 5
	     [D]
	 [N] [C]
	 [Z] [M] [P]
	  1   2   3

	 move 1 from 2 to 1
	 move 3 from 1 to 3
	 move 2 from 2 to 1
	 move 1 from 1 to 2
	 * result => cratesOnTop eg CMZ
	 */

	class Move {
		int quantity;
		int fromStack;
		int toStack;
		Move(String line){
			List<String> words = Pattern
					.compile(" ")
					.splitAsStream(line)
					.collect(Collectors.toList());
			quantity = Integer.parseInt(words.get(1));
			fromStack = Integer.parseInt(words.get(3));
			toStack = Integer.parseInt(words.get(5));
		}
	}
	class Pile {
		int pile; // 1 = left column
		String crates;
		Pile(int pile, String crates){
			pile = pile;
			crates = crates;
		}
		void addCrate(String crate) {
			crates.concat(crate);
		}
	}
	static String title = "SupplyStack - part 1: ";
	
	public int solutionPartOne(List<String> lines) {

		// process all lines
		int count = 0;
		int totalPiles = 0;
		boolean processStacks = true;
		ArrayList<String> stackLines = new ArrayList<>();
		ArrayList<Move> moves = new ArrayList<>();
		for (String line : lines) {
			// skip the blank line
			if (AdventOfCode.isBlankString(line)==true) {
				continue;
			}
			// skip the # of piles line
			if (line.startsWith(" 1")) {
				processStacks = false;
				line = line.replaceAll("\\s+","");
				totalPiles = Integer.parseInt(AdventOfCode.lastXChars(line,1));
				continue;
			}
			if (processStacks) {
				LOG.info("line: " + line);
				line = line.replaceAll("\\]+","");
				LOG.info("line: " + line);
				line = line.replaceAll("\\s\\[+","");
				LOG.info("line: " + line);
				line = line.replaceAll("\\[+","");
				LOG.info("line: " + line);

				stackLines.add(line);
			} else {
				moves.add(new Move(line));
			}
		}

		// process stackLines in reverse
		Collections.reverse(stackLines);
		// init array of piles
		ArrayList<Pile> piles = new ArrayList<>();
		for (int i = 0; i < totalPiles; i++) {
//			piles.add(i,null);
		}
		// stack the piles for each line
		count = 0;
		String crate = "";
		for (String stackLine : stackLines) {
			count++;
			// for all piles in a row eg "  A" or " ZZZ"
			LOG.info("stackLine: " + stackLine);
			for (int i = 0; i < stackLine.length(); i++) {
				crate = stackLine.substring(i,i+1);
				LOG.info("crate: " + crate);
//				Pile pile = piles.get(i);
//				pile.addCrate(crate);
//				piles.set(i, pile);
			}
		}

		for (Pile pile : piles) {
			LOG.info("pile: " + pile.pile + " crates: " +pile.crates);
		}
		return 0;
	}

//			for (int i = 0; i < inventory.size(); i++) {

	public HashMap<Integer, String> convertStackLines(List<String> lines, int total) {
		return null;
	}

	public int solutionPartTwo(List<String> lines) {
		
		LOG.info(title + "lines: " + lines.size() + lineSeparator);
		
		int sum = 0;
		for (String line : lines) {
			// split logic
			List<String> split = Pattern
					                     .compile(",")
					                     .splitAsStream(line)
					                     .collect(Collectors.toList());
//			sum += partlyoverlaps(split);
//			LOG.info(title + "cumulative: " + sum + lineSeparator);
		}
		LOG.info("answer: " + sum);
		return sum;
	}
}
