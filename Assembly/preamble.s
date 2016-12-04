#### PREAMBLE - include globals and print, read ####
	.section	.rodata
_printL:	.string "%d\n"
_readL:	.string "%d"
	.text
	.globl	main
###### "built in" print function
print:
	pushq	%rbp       #save old base pointer
	movq	%rsp, %rbp  #new base pointer is top of stack

	movl	%eax, %esi     #print() input is 2nd param to printf
	movl	$_printL, %edi #address of format string is first param
	movl	$0, %eax       #printf requires eax of 0 
	call	printf         #call C library printf()

	leave              #restore old base pointer
	ret                #return where you were called from

###### "built in" read function
read:
	pushq	%rbp       #save old base pointer
	movq	%rsp, %rbp  #new base pointer is top of stack
	subq	$16, %rsp    #make space for local variable

	leaq	-4(%rbp), %rsi #var to read is  2nd param to scanf
	movq	$_readL, %rdi  #address of format string is first param
	movl	$0, %eax       #scanf requires eax of 0 
	call	scanf          #call C library scanf()
	movl	-4(%rbp), %eax #store scanf return val into eax

	leave              #restore old base pointer
	ret                #return where you were called from
#### END PREAMBLE ####

main:
                    pushq	%rbp
                    movq 	%rsp, %rbp
                    subq 	$16, %rsp

#                    CALL 	read
                    call 	read

#                    STRET	__T0
                    movl 	%eax, -4(%rbp)

#                    MOV  	__T0, __T1
                    movl 	-4(%rbp), %eax
                    movl 	%eax, -8(%rbp)

#                    MOV  	__T1, x
                    movl 	-8(%rbp), %eax
                    movl 	%eax, -16(%rbp)

#                    PARAM	x
                    movl 	-16(%rbp), %eax

#                    CALL 	print
                    call 	print

#                    STRET	__T2
                    movl 	%eax, -12(%rbp)

#                    RET  
                    leave
                    retq 

