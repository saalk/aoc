package com.example.service.aoc22;

import com.example.service.AdventOfCode;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
		String crates = "";
		Pile(int pile){
			this.pile = pile;
		}
		void addCrate(String crate) {
			if (crate.isBlank()) return;
			this.crates = this.crates.concat(crate);
//			LOG.info("addCrate after[" + this.pile + "], crates[" +this.crates+"]");

		}
		String removeCrates(int number) {

			if (number==0) return null;
			int pileLenght = this.crates.length();
			String crates = this.crates.substring(pileLenght-number,pileLenght);
			this.crates = this.crates.substring(0,pileLenght-number);

//			LOG.info("removeCrates after[" + this.pile + "], crates[" + this.crates+"]");
			return crates;
		}

		String giveTopCrate() {
			int pileLenght = this.crates.length();
			if (pileLenght==0) return "";

			return this.crates.substring(
					pileLenght-1,
					pileLenght
					);

		}
	}
	ArrayList<Pile> piles = new ArrayList<>();
	static String title = "SupplyStack - part 1: ";
	public String solutionPartOne(List<String> lines) {

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
				stackLines.add(convertStackLine(line));
			} else {
				moves.add(new Move(line));
			}
		}

		// process stackLines in reverse
		Collections.reverse(stackLines);
		// init array of piles
		for (int i = 0; i < totalPiles; i++) {
			piles.add(i,new Pile(i+1));
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
				Pile pile = piles.get(i);
				pile.addCrate(crate);
				piles.set(i, pile);
			}
		}

		for (Pile pile : piles) {
			LOG.info("pile[" + pile.pile + "], crates[" +pile.crates+"]");
		}
		for (Move move : moves) {
			LOG.info("move quantity[" + move.quantity + "], " + "move fromStack[" + move.fromStack + "], " + "move " +
					"toStack[" + move.toStack + "]");
		}

		// move the stack
		for (Move move : moves) {
//			LOG.info("move quantity[" + move.quantity + "], " + "move fromStack[" + move.fromStack + "], " + "move " +
//					"toStack[" + move.toStack + "]");
			String crates = piles.get(move.fromStack-1).removeCrates(move.quantity);
			// reverse crates
			StringBuffer sbr = new StringBuffer(crates);
			// To reverse the string
			sbr.reverse();
			piles.get(move.toStack-1).addCrate(String.valueOf(sbr));

//			for (Pile pile : piles) {
//				LOG.info("pile[" + pile.pile + "], crates[" +pile.crates+"]");
//			}
		}
		String topCrates ="";
		for (Pile pile : piles) {
			topCrates = topCrates.concat(pile.giveTopCrate());
		}
		LOG.info("topCrates : " + topCrates);
		return topCrates;
	}

	public String convertStackLine(String line) {
		int totalPiles = (line.length()+1)/4;
		String result = "";
		for (int i = 0; i < totalPiles; i++) {
			int position = 1+(i*4); // 0,1,2
			result = result.concat(line.substring(position,position+1));
		}
		return result;
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
