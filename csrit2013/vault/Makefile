#
# Created by gmakemake (Ubuntu Sep  7 2011) on Wed Oct 30 19:25:21 2013
#

#
# Definitions
#

.SUFFIXES:
.SUFFIXES:	.a .o .c .C .cpp .s .S
.c.o:
		$(COMPILE.c) $<
.C.o:
		$(COMPILE.cc) $<
.cpp.o:
		$(COMPILE.cc) $<
.S.s:
		$(CPP) -o $*.s $<
.s.o:
		$(COMPILE.cc) $<
.c.a:
		$(COMPILE.c) -o $% $<
		$(AR) $(ARFLAGS) $@ $%
		$(RM) $%
.C.a:
		$(COMPILE.cc) -o $% $<
		$(AR) $(ARFLAGS) $@ $%
		$(RM) $%
.cpp.a:
		$(COMPILE.cc) -o $% $<
		$(AR) $(ARFLAGS) $@ $%
		$(RM) $%

CC =		gcc
CXX =		g++

RM = rm -f
AR = ar
LINK.c = $(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS)
LINK.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) $(LDFLAGS)
COMPILE.c = $(CC) $(CFLAGS) $(CPPFLAGS) -c
COMPILE.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) -c
CPP = $(CPP) $(CPPFLAGS)
########## Flags from header.mak

CXXFLAGS =	-ggdb -Wall
CFLAGS =	-ggdb -Wall -std=c99
CLIBFLAGS =	
CCLIBFLAGS =	

########## End of flags from header.mak


CPP_FILES =	
C_FILES =	vault.c
PS_FILES =	
S_FILES =	
SOURCEFILES =	$(CPP_FILES) $(C_FILES) $(S_FILES)
.PRECIOUS:	$(SOURCEFILES) $(OBJFILES)
OBJFILES =	entry_tools.o file_tools.o

#
# Main targets
#

all:	manager 

manager:	manager.o $(OBJFILES) vault.o
	$(CC) $(CFLAGS) -o manager manager.o $(OBJFILES) vault.o $(CLIBFLAGS)

#
# Dependencies
#

vault.o:	file_tools.h vault.h

#
# Housekeeping
#

clean:
	-/bin/rm vault.o core 2> /dev/null

realclean:        clean

