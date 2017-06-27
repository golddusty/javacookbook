/* C Program to create the random-access file for the RandomAccessFile example
 * Ian F. Darwin, ian@darwinsys.com
 * $Id: RandomWrite.c,v 1.2 2000/07/03 23:15:45 ian Exp $
 */

#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <machine/endian.h>

const off_t OFFSET = 1234;
const char* FILENAME = "random.dat";
const int MODE = 0644;
const char* MESSAGE = "Ye have sought, and ye have found!\r\n";

int
main(int argc, char **argv) {
	int fd;
	int java_offset;

	if ((fd = creat(FILENAME, MODE)) < 0) {
		perror(FILENAME);
		return 1;
	}

	/* Java's DataStreams etc. are defined to be in network byte order */
	java_offset = htonl(OFFSET);

	if (write(fd, &java_offset, sizeof java_offset) < 0) {
		perror("write");
		return 1;
	}

	if (lseek(fd, OFFSET, SEEK_SET) < 0) {
		perror("seek");
		return 1;
	}

	if (write(fd, MESSAGE, strlen(MESSAGE)) != strlen(MESSAGE)) {
		perror("write2");
		return 1;
	}

	if (close(fd) < 0) {
		perror("close!?");
		return 1;
	}

	return 0;
}
