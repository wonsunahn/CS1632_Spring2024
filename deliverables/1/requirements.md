Requirements for Rent-A-Cat

FUN-COMMAND-PROMPT - Upon launch, the system shall display a prompt showing 5 commands:
1, 2, 3, 4, and 5.  The prompt shall accept one of the numerical commands.  If
the user inputs a string other than those commands, a list of all five
available commands and their respective functionalities shall be displayed.

FUN-1-LIST-COMMAND - Upon the user entering command "1" on the prompt, the
system shall display all cats that are available (that is, not rented).
Initially, there shall be 3 cats available: a cat with name "Jennyanydots" and
ID 1, a cat with name "Old Deuteronomy" and ID 2, and a cat with name
"Mistoffelees" and ID 3.

FUN-2-RENT-COMMAND - Upon the user entering command "2" on the prompt, the
system shall ask the user the ID of the cat to rent.  Upon the user entering an
ID, if the cat with the ID is available, the cat shall be rented out and the
system shall inform the user of that fact.  If the cat with the ID is not
available, the sytem shall inform the user that the cat is not here.

FUN-3-RETURN-COMMAND - Upon the user entering command "3" on the prompt, the
system shall ask the user the ID of the cat to return.  Upon the user entering
an ID, if the cat with the ID is rented out, the cat shall be returned and the
system shall welcome back the cat.  If the cat with the ID is not rented out,
the sytem shall inform the user that the cat is already here.

FUN-4-RENAME-COMMAND - Upon the user entering command "4" on the prompt, the
system shall ask the user the ID of the cat to rename, and subsequently the new
name for the cat.  If the cat with the ID is available, the system shall
perform the name change and greet the cat with the new name.  If the cat with
the ID is rented out, the system shall inform the user that the cat is not here
and not perform the name change.

FUN-5-QUIT-COMMAND - Upon the user entering command "5" on the prompt, the
system shall inform the user that it is closing up shop and then shut down.

FUN-INVALID-CAT-ID - If the user, when prompted for a cat ID, enters a string
that is not a valid cat ID (a number greater than or equal to 1 and less than
or equal to 3), then the system shall respond with the string "Invalid cat
ID.".
